package narrakos.bredex_backend_test.controller;

import narrakos.bredex_backend_test.controller.requestobject.AdCreationRequest;
import narrakos.bredex_backend_test.controller.requestobject.AdSearchRequest;
import narrakos.bredex_backend_test.controller.responseobject.AdCreationResponse;
import narrakos.bredex_backend_test.controller.responseobject.AdResponse;
import narrakos.bredex_backend_test.controller.responseobject.AdSearchResponse;
import narrakos.bredex_backend_test.entity.Ad;
import narrakos.bredex_backend_test.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("ad")
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping
    public ResponseEntity<AdCreationResponse> postAd(@RequestBody AdCreationRequest request) {
        Ad ad = adService.createAd(request);
        String adUrl = adService.buildAdUrlFromAd(ad);
        return ResponseEntity.ok(new AdCreationResponse(adUrl));
    }

    @GetMapping("{id}")
    public ResponseEntity<AdResponse> getAd(@NotNull @PathVariable("id") Long adId) {
        Ad ad = adService.getAd(adId);
        return ResponseEntity.ok(new AdResponse(ad));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAd(@NotNull @PathVariable("id") Long adId) {
        adService.deleteAd(adId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("search")
    public ResponseEntity<AdSearchResponse> searchAds(@RequestBody AdSearchRequest request) {
        List<String> ads = adService.searchAds(request);
        return ResponseEntity.ok(new AdSearchResponse(ads));
    }
}
