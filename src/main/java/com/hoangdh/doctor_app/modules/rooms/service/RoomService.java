package com.hoangdh.doctor_app.modules.rooms.service;

import com.hoangdh.doctor_app.modules.rooms.dto.RoomDto;

import java.util.List;

public interface RoomService {
	List<RoomDto> findAll();

	RoomDto findById(String id);

	RoomDto createRoom(RoomDto roomDto);

	RoomDto updateRoom(RoomDto roomDto);

	void deleteRoom(String id);
}
