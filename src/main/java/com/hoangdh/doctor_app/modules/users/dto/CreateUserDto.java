package com.hoangdh.doctor_app.modules.users.dto;

import com.hoangdh.doctor_app.constants.UserGender;
import com.hoangdh.doctor_app.constants.ValidationMessage;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUserDto {
	@Email(message = ValidationMessage.EMAIL_INVALID)
	@NotBlank(message = ValidationMessage.EMAIL_REQUIRED)
	private String email;

	@NotBlank(message = ValidationMessage.FULL_NAME_REQUIRED)
	@Length(min = 3, max = 50, message = ValidationMessage.FULL_NAME_LENGTH)
	private String fullName;

	@Enumerated(EnumType.STRING)
	private UserGender gender = UserGender.Male;
}
