package com.backgammon.v1.security;

import com.backgammon.v1.security.BackGammonUserDetails;
import com.backgammon.v1.user.UserRepository;
import com.backgammon.v1.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BackGammonUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username)
                              .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return BackGammonUserDetails.build(user);
  }
}
