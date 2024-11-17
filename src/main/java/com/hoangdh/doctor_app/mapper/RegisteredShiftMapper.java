package com.hoangdh.doctor_app.mapper;

import com.hoangdh.doctor_app.entity.Department;
import com.hoangdh.doctor_app.entity.RegisteredShift;
import com.hoangdh.doctor_app.modules.departments.dto.DepartmentDto;
import com.hoangdh.doctor_app.modules.shifts.dto.RegisteredShiftDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisteredShiftMapper extends IBaseMapper<RegisteredShift, RegisteredShiftDto> {
	@Mapping(target = "doctors", ignore = true)
	@Mapping(target = "headDoctor", ignore = true)
	DepartmentDto toDepartmentDto(Department entity);

	@Mapping(target = "shift", ignore = true)
	RegisteredShiftDto toDtoExcludeShift(RegisteredShift entity);
}
