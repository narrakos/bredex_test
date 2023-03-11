package narrakos.bredex_backend_test.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdValidationTest {

    private Validator validator;

    @BeforeEach
    void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testWithMinimalValues() {
        Ad ad = new Ad();

        ad.setBrand("x");
        ad.setType("x");
        ad.setDescription("x");
        ad.setPrice(1L);

        assertAdHasNoConstraintViolations(ad);
    }

    @Test
    void testWithMaximalValues() {
        Ad ad = new Ad();

        ad.setBrand("x".repeat(20));
        ad.setType("x".repeat(20));
        ad.setDescription("x".repeat(200));
        ad.setPrice(9_999_999_999L);

        assertAdHasNoConstraintViolations(ad);
    }

    @Test
    void adWithAllBlankValues() {
        Ad ad = new Ad();

        ad.setBrand("");
        ad.setType("");
        ad.setDescription("");
        ad.setPrice(0L);

        assertViolationsWithMessages(ad,
                Ad.BRAND_BLANK_MESSAGE,
                Ad.TYPE_BLANK_MESSAGE,
                Ad.DESCRIPTION_BLANK_MESSAGE,
                Ad.PRICE_ZERO_MESSAGE);
    }

    @Test
    void adWithAllValuesOverLimit() {
        Ad ad = new Ad();

        ad.setBrand("x".repeat(21));
        ad.setType("x".repeat(21));
        ad.setDescription("x".repeat(201));
        ad.setPrice(10_000_000_000L);

        assertViolationsWithMessages(ad,
                Ad.BRAND_TOO_LONG_MESSAGE,
                Ad.TYPE_TOO_LONG_MESSAGE,
                Ad.DESCRIPTION_TOO_LONG_MESSAGE,
                Ad.PRICE_TOO_LONG_MESSAGE);
    }


    void assertAdHasNoConstraintViolations(Ad ad) {
        Set<ConstraintViolation<Ad>> violations = validator.validate(ad);
        Assertions.assertTrue(violations.isEmpty());
    }

    void assertViolationsWithMessages(Ad ad, String... messages) {
        List<String> violationMessages = validator.validate(ad).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        Assertions.assertEquals(messages.length, violationMessages.size());

        for (String message : messages) {
            Assertions.assertTrue(violationMessages.contains(message));
        }
    }
}
