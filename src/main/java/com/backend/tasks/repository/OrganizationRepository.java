package com.backend.tasks.repository;

import com.backend.tasks.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Long> {
}
