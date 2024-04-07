package vn.hanu.fit.sqa.group3.act1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.hanu.fit.sqa.group3.act1.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long > {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User a " + "Set a.enabled = TRUE WHERE a.email = ?1")
    int enableByEmail(String email);
}
