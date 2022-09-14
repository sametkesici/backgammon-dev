package com.backgammon.v1.user;

import com.backgammon.v1.base.Response;
import com.backgammon.v1.user.mapper.UserMapper;
import com.backgammon.v1.user.mapper.UserRegisterMapper;
import com.backgammon.v1.user.model.User;
import com.backgammon.v1.user.model.UserDto;
import com.backgammon.v1.user.model.UserRegisterDto;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  private final UserRegisterMapper userRegisterMapper;

  private final UserMapper userMapper;

  @PostMapping("/register")
  public ResponseEntity<UserDto> registerUser(@RequestBody UserRegisterDto userRegisterDto){
    User user = userRegisterMapper.mapToEntity(userRegisterDto);
    User persistedUser = userService.registerUser(user);
    return ResponseEntity.ok(userMapper.mapToDto(persistedUser));
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserDto>> responseEntity(){
    return ResponseEntity.ok(userMapper.mapToDtoList(userService.findAllUsers()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
      User user = userService.findById(id);
      return ResponseEntity.ok(userMapper.mapToDto(user));
  }

  @GetMapping("/player/{userName}")
  public ResponseEntity<List<UserDto>> searchUserByUsernameStartingWith(@PathVariable String userName){
    List<User> users = userService.searchUserByUserNameStartingWith(userName);
    return ResponseEntity.ok(userMapper.mapToDtoList(users));
  }

}
