package com.hoangdh.doctor_app.modules.shifts.dto;

import com.hoangdh.doctor_app.common.dto.BaseDto;
import com.hoangdh.doctor_app.modules.doctors.dto.DoctorDto;
import com.hoangdh.doctor_app.modules.rooms.dto.RoomDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisteredShiftDto extends BaseDto {
	private String id;
	private Double shiftPrice;
	private Integer maxNumberOfPatients;
	private Boolean isApproved;

	private ShiftDto shift;
	private DoctorDto doctor;
	private RoomDto room;
	private List<RegisteredShiftTimeSlotDto> timeSlots;
}
