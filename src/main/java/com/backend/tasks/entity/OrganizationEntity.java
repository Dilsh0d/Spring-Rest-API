package com.backend.tasks.entity;

import com.backend.tasks.dto.OrganizationDto;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Dilsh0d Tadjiev on 05.03.2019 11:49.
 */
@Entity
@Table(name = "organization")
public class OrganizationEntity extends BaseEntity{

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "orgId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserEntity> users = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public OrganizationDto getDto(){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(getId());
        organizationDto.setName(getName());
        return organizationDto;
    }
}
