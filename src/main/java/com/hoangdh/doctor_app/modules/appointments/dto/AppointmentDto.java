package com.hoangdh.doctor_app.modules.appointments.dto;

import com.hoangdh.doctor_app.common.dto.BaseDto;
import com.hoangdh.doctor_app.constants.ValidationMessage;
import com.hoangdh.doctor_app.modules.patients.dto.PatientDto;
import com.hoangdh.doctor_app.modules.shifts.dto.RegisteredShiftTimeSlotDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppointmentDto extends BaseDto {
	private String id;
	private String symptoms;

	@NotNull(message = ValidationMessage.TIME_SLOT_REQUIRED)
	private RegisteredShiftTimeSlotDto registeredShiftTimeSlot;
	private PatientDto patient;
}
