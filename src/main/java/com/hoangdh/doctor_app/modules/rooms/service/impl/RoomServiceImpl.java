package com.hoangdh.doctor_app.modules.rooms.service.impl;

import com.hoangdh.doctor_app.common.exception.HttpException;
import com.hoangdh.doctor_app.constants.Message;
import com.hoangdh.doctor_app.entity.Department;
import com.hoangdh.doctor_app.entity.Room;
import com.hoangdh.doctor_app.mapper.RoomMapper;
import com.hoangdh.doctor_app.modules.departments.repository.DepartmentRepository;
import com.hoangdh.doctor_app.modules.rooms.dto.RoomDto;
import com.hoangdh.doctor_app.modules.rooms.repository.RoomRepository;
import com.hoangdh.doctor_app.modules.rooms.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {
	private final RoomRepository roomRepository;
	private final DepartmentRepository departmentRepository;
	private final RoomMapper roomMapper;

	@Override
	public List<RoomDto> findAll() {
		return roomRepository.findAll()
			.stream()
			.map(roomMapper::toDto)
			.collect(Collectors.toList());
	}

	@Override
	public RoomDto findById(String id) {
		return roomRepository.findById(id)
			.map(roomMapper::toDto)
			.orElseThrow(() -> HttpException.notFound(Message.ROOM_NOT_FOUND.getMessage()));
	}

	@Override
	public RoomDto createRoom(RoomDto roomDto) {
		Department department = departmentRepository.findById(roomDto.getDepartment().getId())
			.orElseThrow(() -> HttpException.badRequest(Message.DEPARTMENT_NOT_FOUND.getMessage()));

		if (roomRepository.findRoomByNameAndDepartmentId(roomDto.getName(), department.getId()).isPresent()) {
			throw HttpException.badRequest(Message.ROOM_NAME_ALREADY_EXISTS.getMessage(roomDto.getName(), department.getName()));
		}

		Room room = roomMapper.toEntity(roomDto);
		room.setDepartment(department);

		return roomMapper.toDto(roomRepository.save(room));
	}

	@Override
	public RoomDto updateRoom(RoomDto roomDto) {
		Room room = roomRepository.findById(roomDto.getId())
			.orElseThrow(() -> HttpException.notFound(Message.ROOM_NOT_FOUND.getMessage()));

		Department department = departmentRepository.findById(roomDto.getDepartment().getId())
			.orElseThrow(() -> HttpException.badRequest(Message.DEPARTMENT_NOT_FOUND.getMessage()));

		if (roomRepository.findRoomByNameAndDepartmentId(roomDto.getName(), department.getId()).isPresent()) {
			throw HttpException.badRequest(Message.ROOM_NAME_ALREADY_EXISTS.getMessage(roomDto.getName(), department.getName()));
		}

		roomMapper.merge(room, roomDto);
		room.setDepartment(department);

		return roomMapper.toDto(roomRepository.save(room));
	}

	@Override
	public void deleteRoom(String id) {
		Room room = roomRepository.findById(id)
			.orElseThrow(() -> HttpException.notFound(Message.ROOM_NOT_FOUND.getMessage()));

		roomRepository.delete(room);
	}
}
