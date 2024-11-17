package com.hoangdh.doctor_app.modules.shifts.service;

import com.hoangdh.doctor_app.modules.shifts.dto.RegisteredShiftDto;
import com.hoangdh.doctor_app.modules.shifts.dto.request.RegisterShiftRequestDto;

import java.util.List;

public interface RegisteredShiftService {
	RegisteredShiftDto findById(String registeredShiftId);

	List<RegisteredShiftDto> create(List<RegisterShiftRequestDto> registerShiftRequestDtoList);

	RegisteredShiftDto update(RegisteredShiftDto registeredShiftDto);

	void delete(String registeredShiftId);
}
