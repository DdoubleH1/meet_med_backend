package com.hoangdh.doctor_app.modules.patients.service.impl;

import com.hoangdh.doctor_app.common.exception.HttpException;
import com.hoangdh.doctor_app.common.service.JwtAuthenticationManager;
import com.hoangdh.doctor_app.constants.Message;
import com.hoangdh.doctor_app.entity.Patient;
import com.hoangdh.doctor_app.entity.User;
import com.hoangdh.doctor_app.mapper.PatientMapper;
import com.hoangdh.doctor_app.modules.patients.dto.PatientDto;
import com.hoangdh.doctor_app.modules.patients.repository.PatientRepository;
import com.hoangdh.doctor_app.modules.patients.service.PatientService;
import com.hoangdh.doctor_app.modules.users.dto.UserDto;
import com.hoangdh.doctor_app.modules.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
	private final PatientRepository patientRepository;
	private final UserRepository userRepository;

	private final JwtAuthenticationManager jwtAuthenticationManager;

	private final PatientMapper patientMapper;

	@Override
	public List<PatientDto> findAll() {
		return patientRepository.findAll()
			.stream()
			.map(patientMapper::toDto)
			.toList();
	}

	@Override
	public PatientDto findByUserId(String userId) {
		return patientRepository.findByUserId(userId)
			.map(patientMapper::toDto)
			.orElseThrow(() -> HttpException.badRequest(Message.PATIENT_NOT_FOUND.getMessage()));
	}

	@Override
	public PatientDto create(PatientDto patientDto) {
		String userId = jwtAuthenticationManager.getUserId();
		User user = userRepository.findById(userId)
			.orElseThrow(() -> HttpException.badRequest(Message.USER_NOT_FOUND.getMessage()));

		// Check if user already has a patient profile
		if (user.getPatient() != null) {
			throw HttpException.badRequest(Message.PATIENT_ALREADY_EXISTS.getMessage());
		}

		Patient profile = patientMapper.toEntity(patientDto);
		UserDto userDto = patientDto.getUser();
		user.setAge(userDto.getAge());
		user.setGender(userDto.getGender());
		user.setFullName(userDto.getFullName());
		user.setPhone(userDto.getPhone());
		userRepository.save(user);

		profile.setUser(user);


		return patientMapper.toDto(patientRepository.save(profile));
	}

	@Override
	public PatientDto update(PatientDto patientDto) {
		String userId = jwtAuthenticationManager.getUserId();
		User user = userRepository.findById(userId)
			.orElseThrow(() -> HttpException.badRequest(Message.USER_NOT_FOUND.getMessage()));

		if (user.getPatient() == null) {
			throw HttpException.badRequest(Message.PATIENT_HAS_NOT_BEEN_CREATED.getMessage());
		}

		Patient profile = patientRepository.findByUserId(userId)
			.orElseThrow(() -> HttpException.badRequest(Message.PATIENT_NOT_FOUND.getMessage()));

		patientMapper.merge(profile, patientDto);

		UserDto userDto = patientDto.getUser();
		user.setAge(userDto.getAge());
		user.setGender(userDto.getGender());
		user.setFullName(userDto.getFullName());
		user.setPhone(userDto.getPhone());
		User updatedUser = userRepository.save(user);

		profile.setUser(updatedUser);

		return patientMapper.toDto(patientRepository.save(profile));
	}
}
