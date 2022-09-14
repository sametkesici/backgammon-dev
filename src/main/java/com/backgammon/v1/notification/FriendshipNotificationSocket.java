package com.backgammon.v1.notification;

import com.backgammon.v1.notification.model.FriendRequest;
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
public class FriendshipNotificationSocket {

  private final SocketIONamespace friendshipNamespace;

  @Autowired
  public FriendshipNotificationSocket(SocketIOServer server) {
    friendshipNamespace = server.addNamespace("/friendship");
    friendshipNamespace.addConnectListener(onConnected());
    friendshipNamespace.addDisconnectListener(onDisconnected());
    friendshipNamespace.addEventListener("/notification", FriendRequest.class, onFriendshipRequest());
  }

  private DataListener<FriendRequest> onFriendshipRequest() {
    return (client, data, ackSender) -> {
      //var cookies  = handshakeData.getHttpHeaders().getAll(HttpHeaderNames.COOKIE);
      String username = SocketUtil.getUsernameFromHandshake(client.getHandshakeData());
      friendshipNamespace.getBroadcastOperations().sendEvent(data.getUsername(), username);
    };
  }

  private ConnectListener onConnected() {
    return client -> log.info("connected - friendshiprequest '{}'",
                            SocketUtil.getUsernameFromHandshake(client.getHandshakeData()));
  }

  private DisconnectListener onDisconnected() {
    return client -> log.info("Client[{}] - Disconnected from chat module.",
                            SocketUtil.getUsernameFromHandshake(client.getHandshakeData()));
  }
}
