package narrakos.bredex_backend_test.controller.response;

import narrakos.bredex_backend_test.entity.Ad;

public class AdResponse {

    private String brand;
    private String type;
    private String description;
    private Long price;

    public AdResponse(Ad ad) {
        brand = ad.getBrand();
        type = ad.getType();
        description = ad.getDescription();
        price = ad.getPrice();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
