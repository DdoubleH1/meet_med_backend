package com.hoangdh.doctor_app.modules.patients.service;

import com.hoangdh.doctor_app.modules.patients.dto.PatientDto;

import java.util.List;

public interface PatientService {
	List<PatientDto> findAll();

	PatientDto findByUserId(String userId);

	PatientDto create(PatientDto patientDto);

	PatientDto update(PatientDto patientDto);
}
