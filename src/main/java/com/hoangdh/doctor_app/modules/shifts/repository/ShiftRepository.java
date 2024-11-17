package com.hoangdh.doctor_app.modules.shifts.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.Shift;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ShiftRepository extends BaseRepository<Shift, String> {
	@Query(
		"SELECT CASE WHEN count(s) > 0 THEN true ELSE false END FROM Shift s WHERE s.startTime BETWEEN :startTime AND :endTime"
	)
	boolean existsShiftByStartTimeBetween(@Param("startTime") Instant startTime, @Param("endTime") Instant endTime);

	@Query(
		"SELECT s FROM Shift s WHERE s.startTime >= :startTime AND s.endTime <= :endTime"
	)
	List<Shift> findAllByTimeBetween(@Param("startTime") Instant startTime, @Param("endTime") Instant endTime);
}
