package com.taeyoon.api.domain.user.dto;

import com.taeyoon.api.domain.user.model.enumclass.EnumMemberStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class AdminDto extends UserDto {
	private EnumMemberStatus statusCode;
}
