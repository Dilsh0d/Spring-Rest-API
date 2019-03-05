package com.backend.tasks.service.impl;

import com.backend.tasks.dto.OrganizationDto;
import com.backend.tasks.entity.OrganizationEntity;
import com.backend.tasks.exceptions.NotFound404Exception;
import com.backend.tasks.repository.OrganizationRepository;
import com.backend.tasks.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: Dilsh0d Tadjiev on 05.03.2019 12:35.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public OrganizationDto save(OrganizationDto orgDto) {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setName(orgDto.getName());
        organizationRepository.save(organizationEntity);

        orgDto.setId(organizationEntity.getId());
        return orgDto;
    }

    @Override
    @Transactional
    public OrganizationDto update(OrganizationDto orgDto) {
        Optional<OrganizationEntity> organizationEntityOptional = organizationRepository.findById(orgDto.getId());
        if (!organizationEntityOptional.isPresent()) {
            throw new NotFound404Exception();
        }
        OrganizationEntity organizationEntity = organizationEntityOptional.get();
        organizationEntity.setName(orgDto.getName());
        organizationRepository.save(organizationEntity);

        return orgDto;
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationDto get(Long orgId) {
        Optional<OrganizationEntity> organizationEntityOptional = organizationRepository.findById(orgId);
        if (!organizationEntityOptional.isPresent()) {
            throw new NotFound404Exception();
        }
        return organizationEntityOptional.map(OrganizationEntity::getDto).get();
    }

    @Override
    @Transactional
    public void delete(Long orgId) {
        organizationRepository.deleteById(orgId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationDto> list() {
        List<OrganizationDto> dtos = new ArrayList<>();
        organizationRepository.findAll()
                .forEach(entity -> {
                    OrganizationDto dto = new OrganizationDto();
                    BeanUtils.copyProperties(entity,dto);
                    dtos.add(dto);
                });
        return dtos;
    }
}
