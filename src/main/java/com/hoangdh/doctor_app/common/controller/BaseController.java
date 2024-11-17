package com.hoangdh.doctor_app.common.controller;

import com.hoangdh.doctor_app.common.dto.ResponseDto;
import com.hoangdh.doctor_app.common.service.JwtAuthenticationManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public abstract class BaseController {
	@Autowired
	protected JwtAuthenticationManager jwtAuthenticationManager;

	public ResponseEntity<ResponseDto> createSuccessResponse(ResponseDto responseDto) {
		return ResponseEntity.ok(responseDto);
	}

	public ResponseEntity<ResponseDto> createResponse(HttpStatus httpStatus, ResponseDto responseDto) {
		return new ResponseEntity<>(responseDto, httpStatus);
	}

	public ResponseEntity<ResponseDto> createErrorResponse(ResponseDto message) {
		return new ResponseEntity<>(message, HttpStatus.valueOf(message.getStatusCode()));
	}
}
