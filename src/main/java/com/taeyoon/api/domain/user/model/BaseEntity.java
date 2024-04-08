package com.taeyoon.api.domain.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.taeyoon.api.domain.user.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

	@Column(name = "DEL_YN")
	protected String delYn;

	@Column(name = "CREATED_BY")
	protected Long createdBy;

	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	protected LocalDateTime createdDate;

	@Column(name = "LAST_MODIFIED_BY")
	protected Long lastModifiedBy;

	@UpdateTimestamp
	@Column(name = "LAST_MODIFIED_DATE")
	protected LocalDateTime lastModifiedDate;

	@PrePersist // insert 직전
	public void prePersist() {
		Object principal = getPrincipal();
		long userId = 0;
		if (principal instanceof UserDto user) {
			userId = user.getId();
		}
		if (this.createdBy == null)
			this.createdBy = userId;
		if (this.lastModifiedBy == null)
			this.lastModifiedBy = userId;
	}

	@PreUpdate // update 직전
	public void preUpdate() {
		Object principal = getPrincipal();
		long userId = 0;
		if (principal instanceof UserDto user) {
			userId = user.getId();
		}
		if (this.lastModifiedBy == null)
			this.lastModifiedBy = userId;
	}

	private Object getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getPrincipal();
	}
}
