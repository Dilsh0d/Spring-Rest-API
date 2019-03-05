package com.backend.tasks.controller;

import com.backend.tasks.dto.UserDto;
import com.backend.tasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Dilsh0d Tadjiev on 05.03.2019 12:29.
 */
@RestController
@RequestMapping("/orgs/{orgId}/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    UserDto createOrg(@PathVariable(name = "orgId") Long orgId, @RequestBody UserDto userDto) {
        return userService.save(orgId,userDto);
    }

    @PutMapping(path = "/{userId}")
    public @ResponseBody UserDto updateOrg(@PathVariable(name = "orgId") Long orgId,@PathVariable(name = "userId") Long userId,@RequestBody UserDto userDto) {
        userDto.setId(userId);
        return userService.update(orgId,userDto);
    }

    @GetMapping(path = "/{userId}")
    public @ResponseBody  UserDto getOrg(@PathVariable(name = "orgId") Long orgId, @PathVariable(name = "userId") Long userId) {
        return userService.get(orgId,userId);
    }

    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrg(@PathVariable(name = "orgId") Long orgId, @PathVariable(name = "userId") Long userId) {
        userService.delete(orgId,userId);
    }

    @GetMapping
    public @ResponseBody
    List<UserDto> listOrg(@PathVariable(name = "orgId") Long orgId) {
        return userService.list(orgId);
    }
}
