package com.hoangdh.doctor_app.modules.users.controller;

import com.hoangdh.doctor_app.annotation.PermissionsAllowed;
import com.hoangdh.doctor_app.common.controller.BaseController;
import com.hoangdh.doctor_app.common.dto.ResponseDto;
import com.hoangdh.doctor_app.constants.ControllerPath;
import com.hoangdh.doctor_app.constants.Permissions;
import com.hoangdh.doctor_app.modules.identity_providers.IdentityProviderStrategyFactory;
import com.hoangdh.doctor_app.modules.identity_providers.interfaces.IdentityProviderStrategy;
import com.hoangdh.doctor_app.modules.users.dto.UpdateRoleDto;
import com.hoangdh.doctor_app.modules.users.dto.UserDto;
import com.hoangdh.doctor_app.modules.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerPath.USER_ADMIN_CONTROLLER)
public class UserAdminController extends BaseController {
	private final UserService userService;
	private final IdentityProviderStrategyFactory identityProviderStrategyFactory;

	@GetMapping
	@PermissionsAllowed(Permissions.Users.READ)
	public ResponseEntity<ResponseDto> findAll() {
		return createSuccessResponse(ResponseDto.success(userService.findAll()));
	}

	@GetMapping("/{id}")
	@PermissionsAllowed(Permissions.Users.READ)
	public ResponseEntity<ResponseDto> findById(@PathVariable String id) {
		return createSuccessResponse(ResponseDto.success(userService.findById(id)));
	}

	@PutMapping("/{id}")
	@PermissionsAllowed(Permissions.Users.WRITE)
	public ResponseEntity<ResponseDto> update(@PathVariable String id, @RequestBody @Valid UserDto userDto) {
		return createSuccessResponse(ResponseDto.success(userService.update(id, userDto)));
	}

	@DeleteMapping("/{id}")
	@PermissionsAllowed(Permissions.Users.WRITE)
	public ResponseEntity<ResponseDto> delete(@PathVariable String id) {
		userService.delete(id);
		return createSuccessResponse(ResponseDto.success());
	}

	@PatchMapping("/{id}/reset-password")
	@PermissionsAllowed(Permissions.Users.WRITE)
	public ResponseEntity<ResponseDto> changePassword(
		@PathVariable String id,
		@RequestParam(required = false, defaultValue = IdentityProviderStrategyFactory.DEFAULT_STRATEGY) String identity_provider
	) {
		IdentityProviderStrategy strategy = identityProviderStrategyFactory.getStrategy(identity_provider);
		Map<String, Object> user = strategy.getUserByEmail(userService.findById(id).getEmail());
		String message = strategy.resetUserPassword(user);
		return createSuccessResponse(ResponseDto.success(message));
	}

	@PatchMapping("/{id}/roles")
	@PermissionsAllowed(Permissions.Users.WRITE)
	public ResponseEntity<ResponseDto> updateRoles(
		@PathVariable String id,
		@RequestBody @Valid UpdateRoleDto updateRoleDto,
		@RequestParam(required = false, defaultValue = IdentityProviderStrategyFactory.DEFAULT_STRATEGY) String identity_provider
	) {
		IdentityProviderStrategy strategy = identityProviderStrategyFactory.getStrategy(identity_provider);
		Map<String, Object> user = strategy.getUserByEmail(userService.findById(id).getEmail());
		String message = strategy.assignRole(user, updateRoleDto.getRole());

		return createSuccessResponse(ResponseDto.success(message));
	}
}
