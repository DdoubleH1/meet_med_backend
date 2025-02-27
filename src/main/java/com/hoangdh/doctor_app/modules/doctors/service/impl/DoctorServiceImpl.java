package com.hoangdh.doctor_app.modules.doctors.service.impl;

import com.hoangdh.doctor_app.common.exception.HttpException;
import com.hoangdh.doctor_app.constants.Message;
import com.hoangdh.doctor_app.constants.Role;
import com.hoangdh.doctor_app.entity.*;
import com.hoangdh.doctor_app.mapper.DoctorMapper;
import com.hoangdh.doctor_app.modules.departments.repository.DepartmentRepository;
import com.hoangdh.doctor_app.modules.doctors.dto.CreateDoctorDto;
import com.hoangdh.doctor_app.modules.doctors.dto.DoctorDto;
import com.hoangdh.doctor_app.modules.doctors.dto.UpdateDoctorDto;
import com.hoangdh.doctor_app.modules.doctors.repository.DoctorRepository;
import com.hoangdh.doctor_app.modules.doctors.repository.DoctorShiftPriceByDegreeRepository;
import com.hoangdh.doctor_app.modules.doctors.repository.DoctorShiftPriceByExperienceRepository;
import com.hoangdh.doctor_app.modules.doctors.service.DoctorService;
import com.hoangdh.doctor_app.modules.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
	private final UserRepository userRepository;
	private final DoctorRepository doctorRepository;
	private final DepartmentRepository departmentRepository;
	private final DoctorShiftPriceByDegreeRepository doctorShiftPriceByDegreeRepository;
	private final DoctorShiftPriceByExperienceRepository doctorShiftPriceByExperienceRepository;

	private final DoctorMapper doctorMapper;

	@Override
	public List<DoctorDto> findAll() {
		return doctorRepository.findAll()
			.stream()
			.map(doctorMapper::toDto)
			.collect(Collectors.toList());
	}

	@Override
	public DoctorDto findById(String id) {
		return doctorRepository.findById(id)
			.map(doctorMapper::toDto)
			.orElseThrow(() -> HttpException.notFound(Message.DOCTOR_NOT_FOUND.getMessage()));
	}

	@Override
	public DoctorDto create(CreateDoctorDto createDoctorDto) {
		Optional<User> existing = userRepository.findByEmail(createDoctorDto.getEmail());

		if (existing.isPresent()) {
			throw HttpException.badRequest(Message.USER_EMAIL_ALREADY_EXISTS.getMessage(createDoctorDto.getEmail()));
		}

		Department department = departmentRepository.findById(createDoctorDto.getDepartmentId())
			.orElseThrow(() -> HttpException.notFound(Message.DEPARTMENT_NOT_FOUND.getMessage()));

		User user = new User();
		user.setEmail(createDoctorDto.getEmail());
		user.setFullName(createDoctorDto.getFullName());
		user.setGender(createDoctorDto.getGender());
		user.setRole(Role.Doctor);

		User savedUser = userRepository.save(user);

		Doctor doctor = new Doctor();
		doctor.setYearsOfExperience(createDoctorDto.getYearsOfExperience());
		doctor.setDegree(createDoctorDto.getDegree());
		doctor.setDescription(createDoctorDto.getDescription());
		doctor.setUser(savedUser);
		doctor.setDepartment(department);

		return doctorMapper.toDto(doctorRepository.save(doctor));
	}

	@Override
	public DoctorDto update(String id, UpdateDoctorDto updateDoctorDto) {
		Doctor doctor = this.doctorRepository.findById(id)
			.orElseThrow(() -> HttpException.notFound(Message.DOCTOR_NOT_FOUND.getMessage()));

		Department department = this.departmentRepository.findById(updateDoctorDto.getDepartmentId())
			.orElseThrow(() -> HttpException.notFound(Message.DEPARTMENT_NOT_FOUND.getMessage()));

		doctor.setYearsOfExperience(updateDoctorDto.getYearsOfExperience());
		doctor.setDegree(updateDoctorDto.getDegree());
		doctor.setDescription(updateDoctorDto.getDescription());
		doctor.setDepartment(department);

		return doctorMapper.toDto(doctorRepository.save(doctor));
	}

	@Override
	public void delete(String id) {
		Doctor doctor = doctorRepository.findById(id)
			.orElseThrow(() -> HttpException.notFound(Message.DOCTOR_NOT_FOUND.getMessage()));

		doctorRepository.delete(doctor);
	}

	@Override
	public Double calculateDoctorShiftPrice(Doctor doctor) {
		if (doctor.getDegree() == null || doctor.getYearsOfExperience() == null) {
			return doctorShiftPriceByDegreeRepository.findByDegree(DoctorShiftPriceByDegree.BASE_DEGREE).getBasePrice();
		}

		Double priceByDegree = doctorShiftPriceByDegreeRepository.findByDegree(doctor.getDegree()).getBasePrice();
		List<DoctorShiftPriceByExperience> priceByExperiences = doctorShiftPriceByExperienceRepository.findAllSorted();

		for (DoctorShiftPriceByExperience priceByExperience : priceByExperiences) {
			if (doctor.getYearsOfExperience() >= priceByExperience.getExperience()) {
				return priceByDegree * priceByExperience.getMultiplier();
			}
		}

		return 0.0;
	}
}
