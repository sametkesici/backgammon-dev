package com.backgammon.v1.friendship;

import static java.lang.Boolean.TRUE;

import com.backgammon.v1.auth.AuthService;
import com.backgammon.v1.user.UserService;
import com.backgammon.v1.user.exceptions.FriendShipAlreadyExist;
import com.backgammon.v1.user.exceptions.FriendShipRequestNotFound;
import com.backgammon.v1.friendship.model.FriendShip;
import com.backgammon.v1.friendship.model.FriendShipRequestDto;
import com.backgammon.v1.friendship.model.FriendShipStatus;
import com.backgammon.v1.friendship.model.ReplyFriendShipRequestDto;
import com.backgammon.v1.user.model.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendShipService {

  private final FriendShipRepository friendShipRepository;

  private final UserService userService;

  private final AuthService authService;

  @Transactional
  public void addFriend(FriendShipRequestDto friendShipRequestDto) {
    User user = userService.findUserByUserName(authService.currentUsername());
    User friend = userService.findUserByUserName(friendShipRequestDto.getFriendName());

    if (TRUE.equals(checkFriendShipIsPending(friendShipRequestDto.getFriendName()))) {
      throw new FriendShipAlreadyExist("FriendShip already exist");
    }
    FriendShip friendShip = new FriendShip();
    friendShip.setUser(user);
    friendShip.setFriend(friend);
    friendShip.setStatus(FriendShipStatus.PENDING);
    friendShipRepository.save(friendShip);
  }

  @Transactional
  public void replyFriendRequest(ReplyFriendShipRequestDto replyFriendShipRequestDto) {

    User friend = userService.findUserByUserName(authService.currentUsername());
    User user = userService.findUserByUserName(replyFriendShipRequestDto.getFriendName());
    FriendShipStatus friendShipStatus = replyFriendShipRequestDto.getFriendShipStatus();

    FriendShip friendShip = friendShipRepository
        .findByUserAndFriendAndStatus(user, friend, FriendShipStatus.PENDING)
        .orElseThrow(() -> new FriendShipRequestNotFound("friendship not found"));

    if (friendShipStatus == FriendShipStatus.REJECTED) {
      friendShipRepository.delete(friendShip);
    }

    friendShip.setStatus(friendShipStatus);
    FriendShip reverseFriendShip = FriendShip
        .builder()
        .friend(friendShip.getUser())
        .user(friendShip.getFriend())
        .status(friendShip.getStatus())
        .build();
    friendShipRepository.save(friendShip);
    friendShipRepository.save(reverseFriendShip);
  }

  @Transactional(readOnly = true)
  public List<User> getFriendList() {
    User user = userService.findUserByUserName(authService.currentUsername());
    return user
        .getFriendships()
        .stream()
        .filter(x -> x.getStatus() == FriendShipStatus.APPROVED)
        .map(FriendShip::getFriend)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public Boolean checkFriendShipIsPending(String friendName) {
    User user = userService.findUserByUserName(authService.currentUsername());
    User friend = userService.findUserByUserName(friendName);
    return friendShipRepository.findByUserAndFriendAndStatus(user, friend, FriendShipStatus.PENDING).isPresent();
  }

  @Transactional(readOnly = true)
  public Boolean checkFriendShipIsAccepted(String friendName) {
    User user = userService.findUserByUserName(authService.currentUsername());
    User friend = userService.findUserByUserName(friendName);
    return friendShipRepository.findByUserAndFriendAndStatus(user, friend, FriendShipStatus.APPROVED).isPresent();
  }

  @Transactional(readOnly = true)
  public FriendShip checkFriendShip(String friendName) {
    User user = userService.findUserByUserName(authService.currentUsername());
    User friend = userService.findUserByUserName(friendName);
    return friendShipRepository.findByUserAndFriend(user, friend).orElse(null);
  }
}
