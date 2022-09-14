package com.backgammon.v1.friendship;

import com.backgammon.v1.base.Response;
import com.backgammon.v1.friendship.mapper.FriendShipMapper;
import com.backgammon.v1.friendship.model.FriendShip;
import com.backgammon.v1.friendship.model.FriendShipResponseDto;
import com.backgammon.v1.friendship.model.FriendShipStatus;
import com.backgammon.v1.user.mapper.UserMapper;
import com.backgammon.v1.friendship.model.FriendShipRequestDto;
import com.backgammon.v1.friendship.model.ReplyFriendShipRequestDto;
import com.backgammon.v1.user.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friendship")
public class FriendShipController {

  private final FriendShipService friendShipService;

  private final UserMapper userMapper;

  private final FriendShipMapper friendShipMapper;

  @PostMapping("/add-friend")
  public ResponseEntity<Void> addFriendRequest(@RequestBody FriendShipRequestDto friendShipRequestDto){
    friendShipService.addFriend(friendShipRequestDto);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/reply-friend-request")
  public ResponseEntity<Void> replyFriendRequest(@RequestBody ReplyFriendShipRequestDto replyFriendShipRequestDto){
    friendShipService.replyFriendRequest(replyFriendShipRequestDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/get-friend-list")
  public ResponseEntity<List<User>> getFriendList(@RequestBody String username){
    return ResponseEntity.ok(friendShipService.getFriendList());
  }

  @GetMapping("/check-friendship-is-pending")
  public ResponseEntity<Boolean> checkFriendShipIsPending(@RequestBody String friendName){
    return ResponseEntity.ok(friendShipService.checkFriendShipIsPending(friendName));
  }

  @GetMapping("/check-friendship-is-accepted")
  public ResponseEntity<Boolean> checkFriendShipIsApproved(@RequestBody String friendName){
    return ResponseEntity.ok(friendShipService.checkFriendShipIsAccepted(friendName));
  }

  @GetMapping("/checkStatus/{friendName}")
  public ResponseEntity<FriendShipResponseDto> checkFriendShip(@PathVariable String friendName){
    FriendShip friendShip = friendShipService.checkFriendShip(friendName);
    return ResponseEntity.ok(friendShipMapper.mapToDto(friendShip));
  }


}
