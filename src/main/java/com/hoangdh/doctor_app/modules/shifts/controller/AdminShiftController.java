package com.hoangdh.doctor_app.modules.shifts.controller;

import com.hoangdh.doctor_app.annotation.PermissionsAllowed;
import com.hoangdh.doctor_app.common.controller.BaseController;
import com.hoangdh.doctor_app.common.dto.ResponseDto;
import com.hoangdh.doctor_app.constants.ControllerPath;
import com.hoangdh.doctor_app.constants.Permissions;
import com.hoangdh.doctor_app.modules.shifts.dto.request.GenerateShiftTableRequestDto;
import com.hoangdh.doctor_app.modules.shifts.dto.request.GetShiftListRequestDto;
import com.hoangdh.doctor_app.modules.shifts.service.ShiftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerPath.SHIFT_ADMIN_CONTROLLER)
@RequiredArgsConstructor
public class AdminShiftController extends BaseController {
	private final ShiftService shiftService;

	@GetMapping
	@PermissionsAllowed(permissions = {Permissions.Shift.READ})
	public ResponseEntity<ResponseDto> getShiftList(@RequestBody @Valid GetShiftListRequestDto getShiftListRequestDto) {
		return createSuccessResponse(ResponseDto.success(shiftService.getShiftList(getShiftListRequestDto.getStartDate(), getShiftListRequestDto.getEndDate())));
	}

	@PostMapping("/generate")
	@PermissionsAllowed(permissions = {Permissions.Shift.WRITE})
	public ResponseEntity<ResponseDto> generateShiftTable(@RequestBody @Valid GenerateShiftTableRequestDto dto) {
		shiftService.createShiftTable(dto.getMonth(), dto.getYear());
		return createSuccessResponse(ResponseDto.success());
	}
}
