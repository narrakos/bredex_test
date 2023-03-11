package narrakos.bredex_backend_test.repository;

import narrakos.bredex_backend_test.entity.Ad;
import narrakos.bredex_backend_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findAllByUser(User user);
}