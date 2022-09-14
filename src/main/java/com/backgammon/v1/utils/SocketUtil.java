package com.backgammon.v1.utils;

import com.corundumstudio.socketio.HandshakeData;

public class SocketUtil {

  public static String getUsernameFromHandshake(HandshakeData handshakeData){
    return handshakeData.getSingleUrlParam("username");
  }

}
