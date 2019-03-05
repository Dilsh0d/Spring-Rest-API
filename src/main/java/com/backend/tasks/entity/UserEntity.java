package com.backend.tasks.entity;

import javax.persistence.*;

/**
 * @author: Dilsh0d Tadjiev on 05.03.2019 11:49.
 */
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "org_id")
    private Long orgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id",updatable = false,insertable = false)
    private OrganizationEntity organization;

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

    public OrganizationEntity getOrganization() {
        return organization;
    }
}
