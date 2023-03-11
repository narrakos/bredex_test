package narrakos.bredex_backend_test.controller.request;

import narrakos.bredex_backend_test.entity.Ad;

public class AdCreationRequest {
    private String brand;
    private String type;
    private String description;
    private Long price;

    public AdCreationRequest(String brand, String type, String description, Long price) {
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.price = price;
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
