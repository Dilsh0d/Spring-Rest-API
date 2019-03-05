package com.backend.tasks.service;

import com.backend.tasks.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {
    OrganizationDto save(OrganizationDto orgDto);

    OrganizationDto update(OrganizationDto orgDto);

    OrganizationDto get(Long orgId);

    void delete(Long orgId);

    List<OrganizationDto> list();
}
