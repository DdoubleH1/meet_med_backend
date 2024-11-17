package com.hoangdh.doctor_app.mapper;

import com.hoangdh.doctor_app.entity.User;
import com.hoangdh.doctor_app.modules.users.dto.CreateUserDto;
import com.hoangdh.doctor_app.modules.users.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends IBaseMapper<User, UserDto> {
	@Override
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "role", ignore = true)
	void merge(@MappingTarget User entity, UserDto dto);

	User create(CreateUserDto createUserDto);

	UserDto toDto(User user);
}
