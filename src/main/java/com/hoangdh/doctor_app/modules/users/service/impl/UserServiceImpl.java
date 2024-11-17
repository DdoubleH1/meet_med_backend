package com.hoangdh.doctor_app.modules.users.service.impl;

import com.hoangdh.doctor_app.common.exception.HttpException;
import com.hoangdh.doctor_app.constants.Message;
import com.hoangdh.doctor_app.entity.User;
import com.hoangdh.doctor_app.mapper.UserMapper;
import com.hoangdh.doctor_app.modules.users.dto.UserDto;
import com.hoangdh.doctor_app.modules.users.repository.UserRepository;
import com.hoangdh.doctor_app.modules.users.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public UserDto findById(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> HttpException.notFound(Message.USER_NOT_FOUND.getMessage()));

		return userMapper.toDto(user);
	}

	@Override
	public List<UserDto> findAll() {
		return userRepository.findAll().stream()
			.map(userMapper::toDto)
			.collect(Collectors.toList());
	}

	@Override
	public UserDto update(String id, UserDto userDto) {
		User existingUser = userRepository.findById(id)
			.orElseThrow(() -> HttpException.badRequest(Message.USER_NOT_FOUND.getMessage()));

		existingUser.setFullName(userDto.getFullName());
		existingUser.setPhone(userDto.getPhone());
		existingUser.setAge(userDto.getAge());
		existingUser.setGender(userDto.getGender());

		return userMapper.toDto(userRepository.save(existingUser));
	}

	@Override
	public void delete(String id) {
		User ExistingUser = userRepository.findById(id)
			.orElseThrow(() -> HttpException.badRequest(Message.USER_NOT_FOUND.getMessage()));

		userRepository.delete(ExistingUser);
	}
}
