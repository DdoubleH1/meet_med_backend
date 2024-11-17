package com.hoangdh.doctor_app.modules.users.repository;

import com.hoangdh.doctor_app.common.repository.BaseRepository;
import com.hoangdh.doctor_app.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
	Optional<User> findByEmail(String email);

	Optional<User> findByIdentityProviderId(String identityProviderId);
}
