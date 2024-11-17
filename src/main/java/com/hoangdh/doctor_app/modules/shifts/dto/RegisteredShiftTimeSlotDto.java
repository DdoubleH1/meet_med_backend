package com.hoangdh.doctor_app.modules.shifts.dto;

import com.hoangdh.doctor_app.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisteredShiftTimeSlotDto extends BaseDto {
	private String id;
	private String startTime;
	private String endTime;
}
