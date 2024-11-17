package com.hoangdh.doctor_app.mapper;

import com.hoangdh.doctor_app.entity.Shift;
import com.hoangdh.doctor_app.modules.shifts.dto.ShiftDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftMapper extends IBaseMapper<Shift, ShiftDto> {
	@Override
	ShiftDto toDto(Shift entity);
}
