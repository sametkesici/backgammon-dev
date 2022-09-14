package com.backgammon.v1.user.socket;

import com.backgammon.v1.utils.SocketUtil;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OnlineUsersSocket {

  private final SocketIONamespace namespace;

  //private static final Map<String, SocketIOClient> clientMap = new HashMap<>();

  private final Set<String> onlineUsers;

  @Autowired
  public OnlineUsersSocket(SocketIOServer server, Set<String> onlineUsers) {
    namespace = server.addNamespace("/onlineUsers");
    this.onlineUsers = onlineUsers;
    namespace.addConnectListener(onConnected());
    namespace.addDisconnectListener(onDisconnected());
  }

  private ConnectListener onConnected() {
    return client -> {
      String username = SocketUtil.getUsernameFromHandshake(client.getHandshakeData());
      //clientMap.put(username, client);
      if (StringUtils.isNotEmpty(username) && !username.equals("null")) {
        onlineUsers.add(username);
      }
      log.info("connected - Connected to onlineusers '{}'", username);
      namespace.getBroadcastOperations().sendEvent("", onlineUsers);
    };
  }

  private DisconnectListener onDisconnected() {
    return client -> {
      String username = SocketUtil.getUsernameFromHandshake(client.getHandshakeData());
      onlineUsers.remove(username);
      log.info("Client[{}] - Disconnected from onlineusers.", username);
      namespace.getBroadcastOperations().sendEvent("onlineUsers", onlineUsers);
    };
  }
}