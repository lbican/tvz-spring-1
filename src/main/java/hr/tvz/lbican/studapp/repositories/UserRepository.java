package hr.tvz.lbican.studapp.repositories;

import hr.tvz.lbican.studapp.models.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Transactional
    Optional<AppUser> findOneByUsername(String username);
}
