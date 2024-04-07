package com.taeyoon.api.domain.user.model;

import com.taeyoon.api.domain.user.model.enumclass.EnumMemberStatus;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_MEMBER")
@DiscriminatorValue("TB_MEMBER")
public class MemberEntity extends UserEntity {
	@Column(name = "STATUS_CODE")
	@Enumerated(EnumType.STRING)
	private EnumMemberStatus statusCode;
}
