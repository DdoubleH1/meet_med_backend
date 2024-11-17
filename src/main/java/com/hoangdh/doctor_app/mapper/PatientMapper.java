package com.hoangdh.doctor_app.mapper;

import com.hoangdh.doctor_app.entity.Patient;
import com.hoangdh.doctor_app.modules.patients.dto.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper extends IBaseMapper<Patient, PatientDto> {
	@Override
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	void merge(@MappingTarget Patient entity, PatientDto dto);
}
