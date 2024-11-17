package com.hoangdh.doctor_app.modules.rooms.controller;

import com.hoangdh.doctor_app.annotation.PermissionsAllowed;
import com.hoangdh.doctor_app.common.controller.BaseController;
import com.hoangdh.doctor_app.common.dto.ResponseDto;
import com.hoangdh.doctor_app.constants.ControllerPath;
import com.hoangdh.doctor_app.constants.Permissions;
import com.hoangdh.doctor_app.modules.rooms.dto.RoomDto;
import com.hoangdh.doctor_app.modules.rooms.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ControllerPath.ROOM_ADMIN_CONTROLLER)
@RequiredArgsConstructor
public class AdminRoomController extends BaseController {
	private final RoomService roomService;

	@GetMapping
	@PermissionsAllowed(permissions = {Permissions.Room.READ})
	public ResponseEntity<ResponseDto> findAll() {
		return createSuccessResponse(ResponseDto.success(roomService.findAll()));
	}

	@GetMapping("/{roomId}")
	@PermissionsAllowed(permissions = {Permissions.Room.READ})
	public ResponseEntity<ResponseDto> findById(@PathVariable String roomId) {
		return createSuccessResponse(ResponseDto.success(roomService.findById(roomId)));
	}

	@PostMapping
	@PermissionsAllowed(permissions = {Permissions.Room.WRITE})
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid RoomDto roomDto) {
		return createSuccessResponse(ResponseDto.success(roomService.createRoom(roomDto)));
	}

	@PutMapping("/{roomId}")
	@PermissionsAllowed(permissions = {Permissions.Room.WRITE})
	public ResponseEntity<ResponseDto> update(
		@PathVariable String roomId,
		@RequestBody @Valid RoomDto roomDto
	) {
		roomDto.setId(roomId);
		return createSuccessResponse(ResponseDto.success(roomService.updateRoom(roomDto)));
	}

	@DeleteMapping
	@PermissionsAllowed(permissions = {Permissions.Room.WRITE})
	public ResponseEntity<ResponseDto> delete(@RequestParam String roomId) {
		roomService.deleteRoom(roomId);
		return createSuccessResponse(ResponseDto.success());
	}
}
