package com.backend.tasks.dto;

/**
 * @author: Dilsh0d Tadjiev on 05.03.2019 12:38.
 */
public class UserDto {
    private Long id;
    private String fullname;
    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
