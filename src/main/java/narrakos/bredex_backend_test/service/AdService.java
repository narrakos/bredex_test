package narrakos.bredex_backend_test.service;

import narrakos.bredex_backend_test.entity.Ad;
import narrakos.bredex_backend_test.entity.User;
import narrakos.bredex_backend_test.repository.AdRepository;
import narrakos.bredex_backend_test.repository.UserRepository;
import narrakos.bredex_backend_test.controller.request.AdCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdService {

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

        return adRepository.save(ad);
    }
}
