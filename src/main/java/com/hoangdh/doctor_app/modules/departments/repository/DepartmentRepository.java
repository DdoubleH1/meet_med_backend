package com.hoangdh.doctor_app.modules.departments.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, String> {
	Optional<Department> findByNameIgnoreCase(String name);
}
