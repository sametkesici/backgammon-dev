package com.backgammon.v1.user.mapper;

import com.backgammon.v1.base.BaseMapper;
import com.backgammon.v1.user.model.User;
import com.backgammon.v1.user.model.UserRegisterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper extends BaseMapper<User, UserRegisterDto> {}
