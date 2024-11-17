package com.hoangdh.doctor_app.modules.shifts.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.RegisteredShiftTimeSlot;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredShiftTimeSlotRepository extends BaseRepository<RegisteredShiftTimeSlot, String> {
}
