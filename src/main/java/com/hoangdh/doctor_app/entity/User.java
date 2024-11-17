package com.hoangdh.doctor_app.entity;

import com.hoangdh.doctor_app.constants.Role;
import com.hoangdh.doctor_app.constants.UserGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "tbl_user")
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false, unique = true)
	private String email;

	private String fullName;
	private Integer age;
	private String phone;

	@Column(unique = true)
	private String identityProviderId;
	private String identityProvider;

	@Column(nullable = false)
	private UserGender gender = UserGender.Male;

	@Column(nullable = false)
	private Role role = Role.User;

	@OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Doctor doctor;

	@OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Patient patient;
}
