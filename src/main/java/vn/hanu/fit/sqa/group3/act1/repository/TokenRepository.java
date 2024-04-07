package vn.hanu.fit.sqa.group3.act1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.hanu.fit.sqa.group3.act1.model.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByStr(String str);

    @Transactional
    @Modifying
    @Query("UPDATE Token tk Set tk.confirmed = TRUE WHERE tk.str = ?1")
    int confirm(String str);
}
