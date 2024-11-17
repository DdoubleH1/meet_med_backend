package com.hoangdh.doctor_app.modules.doctors.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends BaseRepository<Doctor, String> {
	@Query("SELECT d FROM Doctor d WHERE d.user.id = :userId")
	Optional<Doctor> findByUserId(String userId);
}
