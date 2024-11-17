package com.hoangdh.doctor_app.modules.departments.service;

import com.hoangdh.doctor_app.modules.departments.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
	List<DepartmentDto> findAll();

	DepartmentDto findById(String id);

	DepartmentDto create(DepartmentDto departmentDto);

	DepartmentDto update(String id, DepartmentDto departmentDto);

	void delete(String id);

	void addHeadDoctor(String departmentId, String doctorId);

	void addDoctors(String departmentId, List<String> doctorIds);

	void removeDoctor(String departmentId, String doctorId);
}
