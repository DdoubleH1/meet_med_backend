package com.hoangdh.doctor_app.mapper;

import com.hoangdh.doctor_app.entity.Department;
import com.hoangdh.doctor_app.entity.Doctor;
import com.hoangdh.doctor_app.entity.Room;
import com.hoangdh.doctor_app.modules.departments.dto.DepartmentDto;
import com.hoangdh.doctor_app.modules.doctors.dto.DoctorDto;
import com.hoangdh.doctor_app.modules.rooms.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper extends IBaseMapper<Room, RoomDto> {
	@Override
	@Mapping(target = "id", ignore = true)
	void merge(@MappingTarget Room entity, RoomDto dto);

	@Mapping(target = "doctors", ignore = true)
	DepartmentDto toDepartmentDto(Department entity);

	@Mapping(target = "department", ignore = true)
	DoctorDto toDoctorDto(Doctor entity);
}
