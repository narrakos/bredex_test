package narrakos.bredex_backend_test.repository;

import narrakos.bredex_backend_test.entity.Ad;
import narrakos.bredex_backend_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findAllByUser(User user);

    @Query(value = "SELECT a FROM Ad a WHERE " +
            "UPPER(a.brand) like UPPER(:brand) AND " +
            "UPPER(a.type) like UPPER(:type)"
    )
    List<Ad> searchAds(String brand, String type);
}
