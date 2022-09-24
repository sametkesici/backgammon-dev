package com.backgammon.v1.game.socket;

import com.backgammon.v1.game.GameService;
import com.backgammon.v1.game.GameService;
import com.backgammon.v1.game.model.GameMove;
import com.backgammon.v1.game.model.Room;
import com.backgammon.v1.user.UserService;
import com.backgammon.v1.utils.SocketUtil;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameSocket {

  private final SocketIONamespace gameSocket;

  private final UserService userService;

  private final GameService gameService;

  @Autowired
  public GameSocket(SocketIOServer server, UserService userService, GameService gameService) {
    gameSocket = server.addNamespace("/game");
    this.userService = userService;
    this.gameService = gameService;
    gameSocket.addConnectListener(onConnected());
    gameSocket.addDisconnectListener(onDisconnected());
    gameSocket.addEventListener("/play", GameMove.class, makeMove());
    gameSocket.addEventListener("/createRoom", Room.class , createRoom());
    gameSocket.addEventListener("/joinRoom" , Room.class , joinRoom());
  }

  private DataListener<GameMove> makeMove() {
    return (client, data, ackSender) -> {
      //var cookies  = handshakeData.getHttpHeaders().getAll(HttpHeaderNames.COOKIE);
      String username = SocketUtil.getUsernameFromHandshake(client.getHandshakeData());
      //gameSocket.getBroadcastOperations().sendEvent(data.getUsername(), username);
    };
  }

  private DataListener<Room> joinRoom() {
    return (client, data, ackSender) -> {
      //String username = SocketUtil.getUsernameFromHandshake(client.getHandshakeData());
      String roomName = data.getUsername();
      client.joinRoom(roomName);
    };
  }

  private DataListener<Room> createRoom() {
    return (client, data, ackSender) -> {
      String username = SocketUtil.getUsernameFromHandshake(client.getHandshakeData());
      client.joinRoom(username);
      client.getAllRooms();
    };
  }

  private ConnectListener onConnected() {
    return client -> {
      String username = SocketUtil.getUsernameFromHandshake(client.getHandshakeData());
    };
  }



  private DisconnectListener onDisconnected() {
    return client -> {

    };
  }
}
