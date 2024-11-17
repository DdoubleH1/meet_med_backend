package com.hoangdh.doctor_app.modules.patients.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends BaseRepository<Patient, String> {
	@Query("SELECT p FROM Patient p WHERE p.user.id = :userId")
	Optional<Patient> findByUserId(String userId);
}
