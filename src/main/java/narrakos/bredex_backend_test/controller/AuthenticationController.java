package narrakos.bredex_backend_test.controller;

import narrakos.bredex_backend_test.controller.requestobject.SignupRequest;
import narrakos.bredex_backend_test.controller.responseobject.AuthenticationResponse;
import narrakos.bredex_backend_test.service.AuthenticationService;
import narrakos.bredex_backend_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<Object> signup(SignupRequest request) {
        userService.saveUser(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestParam("email") String email) {
        String token = authenticationService.loginUser(email);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}
