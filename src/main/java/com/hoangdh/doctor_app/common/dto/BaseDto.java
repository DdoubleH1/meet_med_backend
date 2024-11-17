package com.hoangdh.doctor_app.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BaseDto {
	protected Instant createdAt;
	protected Instant updatedAt;
}
