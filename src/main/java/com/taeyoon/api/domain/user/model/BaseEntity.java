package com.taeyoon.api.domain.user.model;

import com.taeyoon.api.domain.user.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {

    @Column(name = "DEL_YN")
    protected String delYn;

    @CreationTimestamp
    @Column(name = "CREATED_BY")
    protected Long createdBy;

    @Column(name = "CREATED_DATE")
    protected LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "LAST_MODIFIED_BY")
    protected Long lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE")
    protected LocalDateTime lastModifiedDate;

    @PrePersist // insert 직전
    public void prePersist() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = (UserDto)authentication.getPrincipal();
        long userId = user == null ? 0L : user.getId();
        if (this.createdBy == null) this.createdBy = userId;
        if (this.lastModifiedBy == null) this.lastModifiedBy = userId;
        if (this.delYn == null) this.delYn = "N";
    }

    @PreUpdate // update 직전
    public void preUpdate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = (UserDto)authentication.getPrincipal();
        this.lastModifiedBy = user == null ? 0L : user.getId();
    }
}
