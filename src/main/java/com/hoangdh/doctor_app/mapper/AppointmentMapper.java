package com.hoangdh.doctor_app.mapper;

import com.hoangdh.doctor_app.entity.Appointment;
import com.hoangdh.doctor_app.modules.appointments.dto.AppointmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper extends IBaseMapper<Appointment, AppointmentDto> {
}
