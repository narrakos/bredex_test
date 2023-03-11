package narrakos.bredex_backend_test.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ad")
public class Ad {
    public static final String BRAND_BLANK_MESSAGE = "Brand name must be provided";
    public static final String BRAND_TOO_LONG_MESSAGE = "Brand name cannot be longer than 20 characters";
    public static final String TYPE_BLANK_MESSAGE = "Type name must be provided";
    public static final String TYPE_TOO_LONG_MESSAGE = "Type name cannot be longer than 20 characters";
    public static final String DESCRIPTION_BLANK_MESSAGE = "Description must be provided";
    public static final String DESCRIPTION_TOO_LONG_MESSAGE = "Description cannot be longer than 200 characters";
    public static final String PRICE_ZERO_MESSAGE = "Price must be provided";
    public static final String PRICE_TOO_LONG_MESSAGE = "Price must be under 10_000_000_000";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = BRAND_BLANK_MESSAGE)
    @Length(max = 20, message = BRAND_TOO_LONG_MESSAGE)
    private String brand;
    @NotBlank(message = TYPE_BLANK_MESSAGE)
    @Length(max = 20, message = TYPE_TOO_LONG_MESSAGE)
    private String type;
    @NotBlank(message = DESCRIPTION_BLANK_MESSAGE)
    @Length(max = 200, message = DESCRIPTION_TOO_LONG_MESSAGE)
    private String description;
    @Min(value = 1L, message = PRICE_ZERO_MESSAGE)
    @Max(value = 9_999_999_999L, message = PRICE_TOO_LONG_MESSAGE)
    private Long price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Ad() {
    }

    public Ad(String brand, String type, String description, Long price) {
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
