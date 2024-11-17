package com.hoangdh.doctor_app.modules.departments.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddDoctorsDto {
	private List<String> doctorIds;
}
