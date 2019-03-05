package com.backend.tasks.service.impl;

import com.backend.tasks.dto.UserDto;
import com.backend.tasks.entity.OrganizationEntity;
import com.backend.tasks.entity.UserEntity;
import com.backend.tasks.exceptions.NotFound404Exception;
import com.backend.tasks.repository.OrganizationRepository;
import com.backend.tasks.repository.UserRepository;
import com.backend.tasks.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public UserDto save(Long orgId, UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFullname(userDto.getFullname());
        userEntity.setOrgId(orgId);
        userRepository.save(userEntity);

        userDto.setId(userEntity.getId());
        userDto.setOrgId(orgId);

        return userDto;
    }

    @Override
    @Transactional
    public UserDto update(Long orgId, UserDto userDto) {
        Optional<UserEntity> userEntityOptional = userRepository.findByIdAndOrgId(userDto.getId(),orgId);
        if (!userEntityOptional.isPresent()) {
            throw new NotFound404Exception();
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setFullname(userDto.getFullname());
        userRepository.save(userEntity);

        userDto.setOrgId(orgId);
        return userDto;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto get(Long orgId, Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findByIdAndOrgId(userId,orgId);
        if (!userEntityOptional.isPresent()) {
            throw new NotFound404Exception();
        }
        return userEntityOptional.map(UserEntity::getDto).get();
    }

    @Override
    @Transactional
    public void delete(Long orgId, Long userId) {
        userRepository.deleteByIdAndOrgId(userId,orgId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> list(Long orgId) {
        Optional<OrganizationEntity> organizationEntityOptional = organizationRepository.findById(orgId);
        if(!organizationEntityOptional.isPresent()){
            throw new NotFound404Exception();
        }
        List<UserDto> dtos = new ArrayList<>();
        organizationEntityOptional.get().getUsers().forEach(entity -> {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(entity,dto);
            dtos.add(dto);
        });
        return dtos;
    }
}
