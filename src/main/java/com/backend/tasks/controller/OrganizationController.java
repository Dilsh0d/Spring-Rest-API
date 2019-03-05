package com.backend.tasks.controller;

import com.backend.tasks.dto.OrganizationDto;
import com.backend.tasks.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Dilsh0d Tadjiev on 05.03.2019 12:29.
 */
@RestController
@RequestMapping("/orgs")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody OrganizationDto createOrg(@RequestBody OrganizationDto orgDto) {
        return organizationService.save(orgDto);
    }

    @PutMapping(path = "/{orgId}")
    public @ResponseBody OrganizationDto updateOrg(@PathVariable(name = "orgId") Long orgId,@RequestBody OrganizationDto orgDto) {
        orgDto.setId(orgId);
        return organizationService.update(orgDto);
    }

    @GetMapping(path = "/{orgId}")
    public @ResponseBody OrganizationDto getOrg(@PathVariable(name = "orgId") Long orgId) {
        return organizationService.get(orgId);
    }

    @DeleteMapping(path = "/{orgId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrg(@PathVariable(name = "orgId") Long orgId) {
        organizationService.delete(orgId);
    }

    @GetMapping
    public @ResponseBody  List<OrganizationDto> listOrg() {
        return organizationService.list();
    }
}
