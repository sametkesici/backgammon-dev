package com.backgammon.v1.user.mapper;

import com.backgammon.v1.base.BaseMapper;
import com.backgammon.v1.user.model.User;
import com.backgammon.v1.user.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends BaseMapper<User, UserDto> {}
