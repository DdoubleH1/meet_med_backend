package com.hoangdh.doctor_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tbl_registered_shift_time_slot")
public class RegisteredShiftTimeSlot extends BaseEntity {
	public static final Integer MAX_NUMBER_OF_PATIENTS = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private Instant startTime;
	private Instant endTime;

	@ManyToOne
	@JoinColumn(name = "registered_shift_id", referencedColumnName = "id")
	private RegisteredShift registeredShift;
}
