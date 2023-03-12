package narrakos.bredex_backend_test.service;

import narrakos.bredex_backend_test.entity.Token;
import narrakos.bredex_backend_test.entity.User;
import narrakos.bredex_backend_test.repository.TokenRepository;
import narrakos.bredex_backend_test.security.AppUserDetails;
import narrakos.bredex_backend_test.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    //Could be modified to revoke every token before saving the new one sparing the filtering
    public String loginUser(String email) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, "password"));
        AppUserDetails userDetails = (AppUserDetails) userDetailsService.loadUserByUsername(email);

        Token token = jwtService.generateToken(userDetails);
        token.setUser(userDetails.getUser());

        tokenRepository.save(token);
        revokeOldTokens(userDetails.getUser(), token);
        return token.getToken();
    }

    private void revokeOldTokens(User user, Token token) {
        List<Token> oldTokens = tokenRepository.findByUserAndRevokedFalse(user)
                .stream()
                .filter(t -> !t.equals(token) && !t.isRevoked())
                .collect(Collectors.toList());
        oldTokens.forEach(t -> t.setRevoked(true));
        tokenRepository.saveAll(oldTokens);
    }
}
