package com.hoangdh.doctor_app.modules.rooms.dto;

import com.hoangdh.doctor_app.common.dto.BaseDto;
import com.hoangdh.doctor_app.constants.ValidationMessage;
import com.hoangdh.doctor_app.modules.departments.dto.DepartmentDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoomDto extends BaseDto {
	private String id;

	@NotBlank(message = ValidationMessage.ROOM_NAME_REQUIRED)
	@Size(min = 3, max = 50, message = ValidationMessage.ROOM_NAME_LENGTH)
	private String name;

	@NotNull(message = ValidationMessage.ROOM_DEPARTMENT_REQUIRED)
	private DepartmentDto department;
}
