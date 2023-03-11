package narrakos.bredex_backend_test.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    public static final String NAME_BLANK_MESSAGE = "Name must be provided";
    public static final String NAME_TOO_LONG_MESSAGE = "Name cannot be longer than 50 characters";
    public static final String EMAIL_FORMAT_INVALID_MESSAGE = "Email address format is invalid. " +
            "Email can only contain latin letters, numbers and the characters + - _ . &";
    public static final String EMAIL_ADDRESS_TOO_LONG_MESSAGE = "Email address is too long";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    @NotBlank(message = NAME_BLANK_MESSAGE)
    @Size(max = 50, message = NAME_TOO_LONG_MESSAGE)
    private String name;
    /**
     * Accepts upper and lowercase latin letters, numbers and the characters + - _ . &
     */
    //Interesting read: https://david-gilbertson.medium.com/the-100-correct-way-to-validate-email-addresses-7c4818f24643
    //Size limit: https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9+\\-_.&]{1,64}@.{1,255}$", message = EMAIL_FORMAT_INVALID_MESSAGE)
    @Size(max = 254, message = EMAIL_ADDRESS_TOO_LONG_MESSAGE)
    private String email;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email.toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return "password";
    }


    //id or id + email would probably be enough
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getName().equals(user.getName()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail());
    }


}
