package com.hoangdh.doctor_app.modules.departments.controller;

import com.hoangdh.doctor_app.annotation.PermissionsAllowed;
import com.hoangdh.doctor_app.common.controller.BaseController;
import com.hoangdh.doctor_app.common.dto.ResponseDto;
import com.hoangdh.doctor_app.constants.ControllerPath;
import com.hoangdh.doctor_app.constants.Permissions;
import com.hoangdh.doctor_app.modules.departments.dto.AddDoctorsDto;
import com.hoangdh.doctor_app.modules.departments.dto.DepartmentDto;
import com.hoangdh.doctor_app.modules.departments.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerPath.DEPARTMENT_ADMIN_CONTROLLER)
@RequiredArgsConstructor
public class DepartmentAdminController extends BaseController {
	private final DepartmentService departmentService;

	@GetMapping
	@PermissionsAllowed(Permissions.Departments.READ)
	public ResponseEntity<ResponseDto> findAll() {
		return createSuccessResponse(ResponseDto.success(departmentService.findAll()));
	}

	@GetMapping("/{id}")
	@PermissionsAllowed(Permissions.Departments.READ)
	public ResponseEntity<ResponseDto> findById(@PathVariable String id) {
		return createSuccessResponse(ResponseDto.success(departmentService.findById(id)));
	}

	@PostMapping
	@PermissionsAllowed(Permissions.Departments.WRITE)
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid DepartmentDto departmentDto) {
		return createSuccessResponse(ResponseDto.success(departmentService.create(departmentDto)));
	}

	@PutMapping("/{id}")
	@PermissionsAllowed(Permissions.Departments.WRITE)
	public ResponseEntity<ResponseDto> update(@PathVariable String id, @RequestBody @Valid DepartmentDto departmentDto) {
		return createSuccessResponse(ResponseDto.success(departmentService.update(id, departmentDto)));
	}

	@DeleteMapping("/{id}")
	@PermissionsAllowed(Permissions.Departments.WRITE)
	public ResponseEntity<ResponseDto> delete(@PathVariable String id) {
		departmentService.delete(id);
		return createSuccessResponse(ResponseDto.success());
	}

	@PatchMapping("/{departmentId}/head-doctor/{doctorId}")
	public ResponseEntity<ResponseDto> addHeadDoctor(
		@PathVariable String departmentId,
		@PathVariable String doctorId
	) {
		departmentService.addHeadDoctor(departmentId, doctorId);
		return createSuccessResponse(ResponseDto.success());
	}

	@PatchMapping("/{departmentId}/doctors")
	public ResponseEntity<ResponseDto> addDoctors(
		@PathVariable String departmentId,
		@RequestBody @Valid AddDoctorsDto addDoctorsDto
	) {
		departmentService.addDoctors(departmentId, addDoctorsDto.getDoctorIds());
		return createSuccessResponse(ResponseDto.success());
	}

	@PatchMapping("/{departmentId}/doctors/{doctorId}")
	public ResponseEntity<ResponseDto> removeDoctor(
		@PathVariable String departmentId,
		@PathVariable String doctorId
	) {
		departmentService.removeDoctor(departmentId, doctorId);
		return createSuccessResponse(ResponseDto.success());
	}
}
