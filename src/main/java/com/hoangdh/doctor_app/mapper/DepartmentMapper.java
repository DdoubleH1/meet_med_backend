package com.hoangdh.doctor_app.mapper;

import com.hoangdh.doctor_app.entity.Department;
import com.hoangdh.doctor_app.entity.Doctor;
import com.hoangdh.doctor_app.modules.departments.dto.DepartmentDto;
import com.hoangdh.doctor_app.modules.doctors.dto.DoctorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends IBaseMapper<Department, DepartmentDto> {
	@Override
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", expression = "java(com.thanhnd.clinic_application.helper.StringHelper.toTitleCase(dto.getName()))")
	@Mapping(target = "headDoctor", ignore = true)
	void merge(@MappingTarget Department entity, DepartmentDto dto);

	@Mapping(target = "department", ignore = true)
	DoctorDto toDoctorDto(Doctor doctor);
}
