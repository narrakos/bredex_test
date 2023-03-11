package narrakos.bredex_backend_test.repository;

import narrakos.bredex_backend_test.entity.Token;
import narrakos.bredex_backend_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findByUserAndRevokedFalse(User user);
    Optional<Token> findByToken(String token);
}
