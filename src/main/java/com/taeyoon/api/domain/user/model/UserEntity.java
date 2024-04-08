package com.taeyoon.api.domain.user.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
@Inheritance(strategy = InheritanceType.JOINED) // 상속 구현 전략 선택
@DiscriminatorColumn(name = "USER_TYPE_CODE")
public class UserEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_TYPE_CODE", insertable = false, updatable = false)
	private String userTypeCode;

	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "COUNTRY_CODE")
	private String countryCode;
	@Column(name = "TEL_NO")
	private String telNo;
	@Column(name = "REG_DATE")
	private Date regDate;
	@Column(name = "LEAVE_DATE")
	private LocalDateTime leaveDate;
	@Column(name = "ACCOUNT_ID")
	private Long accountId;

}
