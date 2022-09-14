package com.backgammon.v1.user;

import com.backgammon.v1.auth.AuthService;
import com.backgammon.v1.user.exceptions.UserAlreadyRegisteredException;
import com.backgammon.v1.user.exceptions.UserNotFoundException;
import com.backgammon.v1.user.model.Role;
import com.backgammon.v1.user.model.RoleType;
import com.backgammon.v1.user.model.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthService authService;

  private final RoleRepository roleRepository;

  @Transactional(readOnly = true)
  public User findById(Long userId){
    return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
  }

  @Transactional
  public User registerUser(User user) {
    Role userRole = roleRepository.findByRoleName(RoleType.USER);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(new HashSet<>(List.of(userRole)));
    return userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public List<User> findAllUsers(){
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public User findUserByUserName(String userName){
    return userRepository.findByUserName(userName).orElseThrow(() -> new UserNotFoundException("user not found"));
  }

  @Transactional(readOnly = true)
  public List<User> searchUserByUserNameStartingWith(String userName){
    List<User> userList =  userRepository.findByUserNameStartingWithIgnoreCase(userName);

    if(authService.isAuth()){
      return userList.stream().filter(user -> !user.getUserName().equals(authService.currentUsername())).collect(
          Collectors.toList());
    }

    return userList;
  }
}
