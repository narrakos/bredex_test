package narrakos.bredex_backend_test.controller.response;

public class AdCreationResponse {
    private String url;

    public AdCreationResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
