package narrakos.bredex_backend_test.controller.responseobject;

import java.util.List;

public class AdSearchResponse {
    private List<String> ads;

    public AdSearchResponse(List<String> ads) {
        this.ads = ads;
    }

    public List<String> getAds() {
        return ads;
    }

    public void setAds(List<String> ads) {
        this.ads = ads;
    }
}
