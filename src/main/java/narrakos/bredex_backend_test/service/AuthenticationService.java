package narrakos.bredex_backend_test.service;

import narrakos.bredex_backend_test.entity.User;
import narrakos.bredex_backend_test.repository.UserRepository;
import narrakos.bredex_backend_test.security.AppUserDetails;
import narrakos.bredex_backend_test.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String loginUser(String email) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, "password"));
        User user = userRepository.findByEmail(email).orElseThrow();
        return jwtService.generateToken(new AppUserDetails(user));
    }
}
