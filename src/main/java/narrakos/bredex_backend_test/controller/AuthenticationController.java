package narrakos.bredex_backend_test.controller;

import narrakos.bredex_backend_test.entity.User;
import narrakos.bredex_backend_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestParam("name") String name,
                                         @RequestParam("email") String email) {

        User user = new User(name, email);

        try {
            userRepository.save(user);
        } catch (ConstraintViolationException e) {
            List<String> violationMessages = e.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(violationMessages);
        }

        return ResponseEntity.ok().build();
    }

}
