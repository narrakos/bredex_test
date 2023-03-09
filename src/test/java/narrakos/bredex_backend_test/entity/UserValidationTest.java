package narrakos.bredex_backend_test.entity;

import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class UserValidationTest {

    private Validator validator;

    @BeforeEach
    void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void userWithShortName() {
        User user = new User("a", "xy@gmail.com");

        assertUserHasNoConstraintViolations(user);
    }

    @Test
    void userWithLongestValidName() {
        User user = new User("x".repeat(50), "xy@gmail.com");

        assertUserHasNoConstraintViolations(user);
    }

    @Test
    void userWithBlankName() {
        User user = new User(" ", "xy@gmail.com");

        assertViolationsWithMessages(user, User.NAME_BLANK_MESSAGE);
    }

    @Test
    void userWithTooLongName() {
        User user = new User("x".repeat(51), "xy@gmail.com");

        assertViolationsWithMessages(user, User.NAME_TOO_LONG_MESSAGE);
    }

    @Test
    void userWithBlankEmail() {
        User user = new User("John Doe", "");

        assertViolationsWithMessages(user, User.EMAIL_FORMAT_INVALID_MESSAGE);
    }

    @Test
    void userWithEmptyLocalEmailPart() {
        User user = new User("John Doe", "@gmail.com");

        assertViolationsWithMessages(user, User.EMAIL_FORMAT_INVALID_MESSAGE);
    }

    @Test
    void userWithEmptyDomainEmailPart() {
        User user = new User("John Doe", "john.doe");

        assertViolationsWithMessages(user, User.EMAIL_FORMAT_INVALID_MESSAGE);
    }

    @Test
    void userWithTooLongLocalEmailPart() {
        User user = new User("John Doe", "x".repeat(65) + "@gmail.com");

        assertViolationsWithMessages(user, User.EMAIL_FORMAT_INVALID_MESSAGE);
    }

    @Test
    void userWithTooLongDomainEmailPartAndTooLongEmail() {
        User user = new User("John Doe", "j@" + "x".repeat(256));

        assertViolationsWithMessages(user,
                User.EMAIL_FORMAT_INVALID_MESSAGE,
                User.EMAIL_ADDRESS_TOO_LONG_MESSAGE);
    }

    @Test
    void userWithLongestValidEmail() {
        String email = "asd"+ "@" + "x".repeat(250); // length == 254
        User user = new User("John Doe", email);

        assertUserHasNoConstraintViolations(user);
    }

    @Test
    void userWithTooLongValidFormatEmail() {
        String email = "asdf"+ "@" + "x".repeat(250); // length == 255
        User user = new User("John Doe", email);

        assertViolationsWithMessages(user, User.EMAIL_ADDRESS_TOO_LONG_MESSAGE);
    }


    void assertUserHasNoConstraintViolations(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertTrue(violations.isEmpty());
    }

    void assertViolationsWithMessages(User user, String... messages){
        List<String> violationMessages = validator.validate(user).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        Assertions.assertEquals(messages.length, violationMessages.size());

        for (String message : messages) {
            Assertions.assertTrue(violationMessages.contains(message));
        }
    }

}