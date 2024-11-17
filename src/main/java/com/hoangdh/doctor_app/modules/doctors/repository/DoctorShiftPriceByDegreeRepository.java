package com.hoangdh.doctor_app.modules.doctors.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.DoctorShiftPriceByDegree;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorShiftPriceByDegreeRepository extends BaseRepository<DoctorShiftPriceByDegree, String> {
	DoctorShiftPriceByDegree findByDegree(String degree);
}
