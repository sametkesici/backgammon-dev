package com.backgammon.v1.user;

import com.backgammon.v1.user.exceptions.UserAlreadyRegisteredException;
import com.backgammon.v1.user.exceptions.UserNotFoundException;
import com.backgammon.v1.user.model.Role;
import com.backgammon.v1.user.model.User;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public User findById(Long userId){
    return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
  }

  @Transactional
  public User registerUser(User user) {
    Role userRole = new Role();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(new HashSet<>(List.of(userRole)));
    return userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public List<User> findAllUsers(){
    return userRepository.findAll();
  }

}
