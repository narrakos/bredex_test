package narrakos.bredex_backend_test.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import narrakos.bredex_backend_test.entity.Token;
import narrakos.bredex_backend_test.exceptions.InvalidTokenException;
import narrakos.bredex_backend_test.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "38792F423F4528482B4D6251655468576D5A7134743777217A24432646294A40";
    private static final int TOKEN_EXPIRATION_IN_MS = 60_000 * 60; // 60 min
    private final TokenRepository tokenRepository;

    @Autowired
    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token generateToken(UserDetails userDetails) {
        Date issued = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_IN_MS);

        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issued)
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        return new Token(token, issued, expiration);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);

        return email.equals(userDetails.getUsername())
                && isTokenNotExpired(token)
                && isTokenInDatabaseAndNotRevoked(token);
    }

    private boolean isTokenInDatabaseAndNotRevoked(String token) {
        return !tokenRepository.findByToken(token).orElseThrow(InvalidTokenException::new).isRevoked();
    }

    private boolean isTokenNotExpired(String token) {
        return extractExpiration(token).after(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
