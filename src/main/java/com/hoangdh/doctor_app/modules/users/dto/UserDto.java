package com.hoangdh.doctor_app.modules.users.dto;

import com.hoangdh.doctor_app.common.dto.BaseDto;
import com.hoangdh.doctor_app.constants.Role;
import com.hoangdh.doctor_app.constants.UserGender;
import com.hoangdh.doctor_app.constants.ValidationMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {
	private String id;

	@NotBlank(message = ValidationMessage.EMAIL_REQUIRED)
	@Email(message = ValidationMessage.EMAIL_INVALID)
	private String email;

	@NotBlank(message = ValidationMessage.FULL_NAME_REQUIRED)
	@Length(min = 3, max = 50, message = ValidationMessage.FULL_NAME_LENGTH)
	private String fullName;

	private Integer age;
	private String phone;
	private UserGender gender = UserGender.Male;
	private Role role;
}
