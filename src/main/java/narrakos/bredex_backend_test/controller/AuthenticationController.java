package narrakos.bredex_backend_test.controller;

import narrakos.bredex_backend_test.entity.User;
import narrakos.bredex_backend_test.exceptions.AppConstraintViolationException;
import narrakos.bredex_backend_test.repository.UserRepository;
import narrakos.bredex_backend_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestParam("name") String name,
                                         @RequestParam("email") String email) {

        User user = new User(name, email);

        try {
            userService.saveUser(user);
        } catch (ConstraintViolationException e) {
            String violationMessages = e.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));

            throw new AppConstraintViolationException(violationMessages);
        }

        return ResponseEntity.ok().build();
    }


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestParam("email") String email) {
        String token = userService.loginUser(email);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private class AuthenticationResponse {
        private String token;

        public AuthenticationResponse() {
        }

        public AuthenticationResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
