package com.backgammon.v1.game;

import com.backgammon.v1.game.model.request.ReplyGameInviteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/game")
public class GameController {

  private final GameService gameService;

  private final GameRepository gameRepository;

  @GetMapping("/invite")
  public ResponseEntity<String> inviteToGame(@RequestParam String opponentUser) {
    String url = gameService.inviteToGame(opponentUser);
    return ResponseEntity.ok(url);
  }

  @PostMapping("/reply-game-invite")
  public ResponseEntity<String> replyGameInvite(
      @RequestBody ReplyGameInviteRequest replyGameInviteRequestDto) {
    String url = gameService.replyInvite(replyGameInviteRequestDto);
    return ResponseEntity.ok(url);
  }
}
