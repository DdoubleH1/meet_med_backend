package com.hoangdh.doctor_app.modules.doctors.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.DoctorShiftPriceByExperience;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorShiftPriceByExperienceRepository extends BaseRepository<DoctorShiftPriceByExperience, String> {
	@Query("SELECT ds FROM DoctorShiftPriceByExperience ds ORDER BY ds.experience")
	List<DoctorShiftPriceByExperience> findAllSorted();
}
