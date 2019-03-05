package com.backend.tasks.repository;

import com.backend.tasks.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByIdAndOrgId(Long id, Long orgId);

    void deleteByIdAndOrgId(Long userId, Long orgId);
}
