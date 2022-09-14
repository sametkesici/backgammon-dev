package com.backgammon.v1.friendship.mapper;

import com.backgammon.v1.base.BaseMapper;
import com.backgammon.v1.friendship.model.FriendShip;
import com.backgammon.v1.friendship.model.FriendShipResponseDto;
import com.backgammon.v1.user.mapper.UserMapper;
import com.backgammon.v1.user.model.User;
import com.backgammon.v1.user.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class , FriendShipMapper.class})
public interface FriendShipMapper extends BaseMapper<FriendShip, FriendShipResponseDto> {}
