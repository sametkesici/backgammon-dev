package com.backgammon.v1.user;

import com.backgammon.v1.user.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByUserName(String userName);

  Optional<User> findByEmail(String email);

}
