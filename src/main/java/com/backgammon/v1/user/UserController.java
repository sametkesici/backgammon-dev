package com.backgammon.v1.user;

import com.backgammon.v1.base.Response;
import com.backgammon.v1.user.mapper.UserMapper;
import com.backgammon.v1.user.mapper.UserRegisterMapper;
import com.backgammon.v1.user.model.User;
import com.backgammon.v1.user.model.UserDto;
import com.backgammon.v1.user.model.UserRegisterDto;
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
@RequestMapping("/api/user")
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

  @PostMapping("/login")
  public ResponseEntity<String> login(){
      return ResponseEntity.ok("successfully logged in");
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
      User user = userService.findById(id);
      return ResponseEntity.ok(userMapper.mapToDto(user));
  }

}
