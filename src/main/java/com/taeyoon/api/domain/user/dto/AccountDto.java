package com.taeyoon.api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	private Long id;
	private String provider;
	private String loginId;
	private String password;
	private String userTypeCode;
	private Long userId;
}
