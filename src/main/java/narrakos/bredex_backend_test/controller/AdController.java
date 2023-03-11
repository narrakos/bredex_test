package narrakos.bredex_backend_test.controller;

import narrakos.bredex_backend_test.controller.response.AdCreationResponse;
import narrakos.bredex_backend_test.entity.Ad;
import narrakos.bredex_backend_test.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

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

    private String buildAdUrl(Ad ad, HttpServletRequest httpRequest) {
        return ServletUriComponentsBuilder
                .fromRequestUri(httpRequest)
                .replacePath(null)
                .pathSegment("ad/" + ad.getId())
                .build()
                .toUriString();
    }
}
