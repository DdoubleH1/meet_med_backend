package com.hoangdh.doctor_app.mapper;

import com.hoangdh.doctor_app.entity.Department;
import com.hoangdh.doctor_app.entity.Doctor;
import com.hoangdh.doctor_app.modules.departments.dto.DepartmentDto;
import com.hoangdh.doctor_app.modules.doctors.dto.DoctorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper extends IBaseMapper<Doctor, DoctorDto> {
	@Override
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "user", ignore = true)
	void merge(@MappingTarget Doctor entity, DoctorDto dto);

	@Mapping(target = "doctors", ignore = true)
	@Mapping(target = "headDoctor", ignore = true)
	DepartmentDto toDepartmentDto(Department department);
}
