package com.hoangdh.doctor_app.modules.departments.dto;

import com.hoangdh.doctor_app.common.dto.BaseDto;
import com.hoangdh.doctor_app.constants.ValidationMessage;
import com.hoangdh.doctor_app.modules.doctors.dto.DoctorDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentDto extends BaseDto {
	private String id;

	@NotBlank(message = ValidationMessage.DEPARTMENT_NAME_REQUIRED)
	@Size(min = 3, max = 50, message = ValidationMessage.DEPARTMENT_NAME_LENGTH)
	private String name;

	@NotBlank(message = ValidationMessage.DEPARTMENT_DESCRIPTION_REQUIRED)
	@Size(max = 2000, message = ValidationMessage.DEPARTMENT_DESCRIPTION_LENGTH)
	private String description;

	private List<DoctorDto> doctors;

	private DoctorDto headDoctor;
}
