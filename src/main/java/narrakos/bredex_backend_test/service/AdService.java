package narrakos.bredex_backend_test.service;

import narrakos.bredex_backend_test.controller.requestobject.AdCreationRequest;
import narrakos.bredex_backend_test.controller.requestobject.AdSearchRequest;
import narrakos.bredex_backend_test.entity.Ad;
import narrakos.bredex_backend_test.entity.User;
import narrakos.bredex_backend_test.exceptions.InvalidTokenException;
import narrakos.bredex_backend_test.repository.AdRepository;
import narrakos.bredex_backend_test.repository.UserRepository;
import narrakos.bredex_backend_test.util.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService {

    private static final String BASE_URL = "http://localhost:8080";

    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdService(AdRepository adRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    public Ad createAd(AdCreationRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        Ad ad = new Ad();
        ad.setBrand(request.getBrand());
        ad.setType(request.getType());
        ad.setDescription(request.getDescription());
        ad.setPrice(request.getPrice());
        ad.setUser(user);

        ObjectValidator.validateObject(ad);

        return adRepository.save(ad);
    }

    public Ad getAd(Long adId) {
        return adRepository.findById(adId).orElseThrow();
    }

    public void deleteAd(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow();

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User savedUser = userRepository.findByEmail(userEmail).orElseThrow();

        if (!ad.getUser().equals(savedUser)) {
            throw new InvalidTokenException();
        }

        adRepository.delete(ad);
    }

    public List<String> searchAds(AdSearchRequest request) {
        List<Ad> ads = adRepository.searchAds(
                "%" + request.getBrand() + "%",
                "%" + request.getType() + "%"
        );

        return ads.stream().map(this::buildAdUrlFromAd).collect(Collectors.toList());
    }

    public String buildAdUrlFromAd(Ad ad) {
        return BASE_URL + "/ad/" + ad.getId();
    }
}
