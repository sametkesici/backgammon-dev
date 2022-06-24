package com.backgammon.v1.security.jwt;

import com.backgammon.v1.user.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken , Long> {

  Optional<RefreshToken> findByToken(String token);

  Integer deleteByUser(User user);
}
