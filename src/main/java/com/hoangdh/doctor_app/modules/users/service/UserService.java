package com.hoangdh.doctor_app.modules.users.service;

import com.hoangdh.doctor_app.modules.users.dto.UserDto;

import java.util.List;

public interface UserService {
	UserDto findById(String id);

	List<UserDto> findAll();

	UserDto update(String id, UserDto userDto);

	void delete(String id);
}
