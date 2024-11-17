package com.hoangdh.doctor_app.modules.users.controller;

import com.hoangdh.doctor_app.common.controller.BaseController;
import com.hoangdh.doctor_app.common.dto.ResponseDto;
import com.hoangdh.doctor_app.common.exception.HttpException;
import com.hoangdh.doctor_app.constants.ControllerPath;
import com.hoangdh.doctor_app.constants.Message;
import com.hoangdh.doctor_app.modules.users.dto.UserDto;
import com.hoangdh.doctor_app.modules.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerPath.USER_CONTROLLER)
@RequiredArgsConstructor
public class UserController extends BaseController {
	private final UserService userService;

	@PutMapping("/profile")
	public ResponseEntity<ResponseDto> updateProfile(@RequestBody UserDto userDto) {
		String userId = jwtAuthenticationManager.getUserId();

		try {
			UserDto user = userService.findById(userId);
			return createSuccessResponse(ResponseDto.success(userService.update(user.getId(), userDto)));
		} catch (HttpException exception) {
			return createErrorResponse(ResponseDto.forbidden(Message.PERMISSION_DENIED.getMessage()));
		}
	}
}
