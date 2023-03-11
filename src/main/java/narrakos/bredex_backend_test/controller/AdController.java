package narrakos.bredex_backend_test.controller;

import narrakos.bredex_backend_test.controller.request.AdCreationRequest;
import narrakos.bredex_backend_test.controller.response.AdCreationResponse;
import narrakos.bredex_backend_test.controller.response.AdResponse;
import narrakos.bredex_backend_test.entity.Ad;
import narrakos.bredex_backend_test.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("/ad")
    public ResponseEntity<AdCreationResponse> postAd(@RequestBody AdCreationRequest request,
                                                     HttpServletRequest httpRequest) {
        Ad ad = adService.createAd(request);
        String adUrl = buildAdUrl(ad, httpRequest);
        return ResponseEntity.ok(new AdCreationResponse(adUrl));
    }

    @GetMapping("/ad/{id}")
    public ResponseEntity<AdResponse> getAd(@NotNull @PathVariable("id") Long adId) {
        Ad ad = adService.getAd(adId);
        return ResponseEntity.ok(new AdResponse(ad));
    }

    @DeleteMapping("/ad/{id}")
    public ResponseEntity<Object> deleteAd(@NotNull @PathVariable("id") Long adId) {
        adService.deleteAd(adId);
        return ResponseEntity.ok().build();
    }

    private String buildAdUrl(Ad ad, HttpServletRequest httpRequest) {
        return ServletUriComponentsBuilder
                .fromRequestUri(httpRequest)
                .replacePath(null)
                .pathSegment("ad/" + ad.getId())
                .build()
                .toUriString();
    }
}
