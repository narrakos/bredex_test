package narrakos.bredex_backend_test.service;

import narrakos.bredex_backend_test.entity.Token;
import narrakos.bredex_backend_test.repository.TokenRepository;
import narrakos.bredex_backend_test.security.AppUserDetails;
import narrakos.bredex_backend_test.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Autowired
    public AuthenticationService(UserDetailsService userDetailsService, JwtService jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    public String loginUser(String email) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, "password"));
        AppUserDetails userDetails = (AppUserDetails) userDetailsService.loadUserByUsername(email);
        Token token = jwtService.generateToken(userDetails);
        token.setUser(userDetails.getUser());
        tokenRepository.save(token);
        return token.getToken();
    }
}
