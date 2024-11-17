package com.hoangdh.doctor_app.modules.shifts.dto.request;

import lombok.Getter;

@Getter
public class UpdateRegisteredShiftRequestDto {
	private Double shiftPrice;
	private Integer maxNumberOfPatients;
}
