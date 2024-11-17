package com.hoangdh.doctor_app.modules.shifts.dto;

import com.hoangdh.doctor_app.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShiftDto extends BaseDto {
	private String id;
	private Instant startTime;
	private Instant endTime;
}
