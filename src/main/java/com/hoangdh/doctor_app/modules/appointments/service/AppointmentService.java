package com.hoangdh.doctor_app.modules.appointments.service;

import com.hoangdh.doctor_app.modules.appointments.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
	List<AppointmentDto> findAllInRegisteredShift(String registeredShiftId);

	List<AppointmentDto> findAllByUserId(String userId);

	AppointmentDto findById(String id);

	AppointmentDto create(AppointmentDto appointmentDto);

	AppointmentDto update(AppointmentDto appointmentDto);

	void delete(String id);
}
