package com.hoangdh.doctor_app.modules.doctors.service;

import com.hoangdh.doctor_app.entity.Doctor;
import com.hoangdh.doctor_app.modules.doctors.dto.CreateDoctorDto;
import com.hoangdh.doctor_app.modules.doctors.dto.DoctorDto;
import com.hoangdh.doctor_app.modules.doctors.dto.UpdateDoctorDto;

import java.util.List;

public interface DoctorService {
	List<DoctorDto> findAll();

	DoctorDto findById(String id);

	DoctorDto create(CreateDoctorDto createDoctorDto);

	DoctorDto update(String id, UpdateDoctorDto updateDoctorDto);

	void delete(String id);

	Double calculateDoctorShiftPrice(Doctor doctor);
}
