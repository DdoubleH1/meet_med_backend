package com.hoangdh.doctor_app.modules.appointments.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends BaseRepository<Appointment, String> {
	List<Appointment> findAllByRegisteredShiftTimeSlotId(String registeredShiftTimeSlotId);

	@Query("SELECT a FROM Appointment a WHERE a.patient.user.id = :userId")
	List<Appointment> findAllByUserId(String userId);

	@Query("SELECT a FROM Appointment a WHERE a.registeredShiftTimeSlot.registeredShift.id = :registeredShiftId")
	List<Appointment> findAllByRegisteredShiftId(String registeredShiftId);
}
