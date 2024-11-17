package com.hoangdh.doctor_app.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E, T> extends JpaRepository<E, T> {
}
