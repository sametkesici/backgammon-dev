package com.backgammon.v1.notification;

import com.backgammon.v1.notification.model.GameInvite;
import com.backgammon.v1.utils.SocketUtil;
import com.corundumstudio.socketio.HandshakeData;
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
public class GameInviteSocket {

  private final SocketIONamespace namespace;

  @Autowired
  public GameInviteSocket(SocketIOServer server) {
    namespace = server.addNamespace("/gameInvite");
    namespace.addConnectListener(onConnected());
    namespace.addDisconnectListener(onDisconnected());
    namespace.addEventListener("/inviteToGame", GameInvite.class, onGameInvite());
  }

  private DataListener<GameInvite> onGameInvite() {
    return (client, data, ackSender) -> {
      String username = SocketUtil.getUsernameFromHandshake(client.getHandshakeData());
      namespace.getBroadcastOperations().sendEvent(data.getUsername(), username);
    };
  }

  private ConnectListener onConnected() {
    return client -> log.info("Connected - gameInvite socket '{}'",
                            SocketUtil.getUsernameFromHandshake(client.getHandshakeData()));
  }

  private DisconnectListener onDisconnected() {
    return client -> log.info("Client[{}] - Disconnected from chat module.",
                            SocketUtil.getUsernameFromHandshake(client.getHandshakeData()));
  }
}
