package com.backend.tasks.service;

import com.backend.tasks.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(Long orgId, UserDto userDto);

    UserDto update(Long orgId, UserDto userDto);

    UserDto get(Long orgId, Long userId);

    void delete(Long orgId, Long userId);

    List<UserDto> list(Long orgId);
}
