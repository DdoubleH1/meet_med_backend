package com.hoangdh.doctor_app.modules.shifts.dto.request;

import com.hoangdh.doctor_app.constants.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterShiftRequestDto {
	@NotBlank(message = ValidationMessage.SHIFT_REQUIRED)
	private String id;
}
