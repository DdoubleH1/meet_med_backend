package com.hoangdh.doctor_app.modules.rooms.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends BaseRepository<Room, String> {
	Optional<Room> findRoomByNameAndDepartmentId(String name, String departmentId);

	List<Room> findAllByDepartmentId(String departmentId);
}
