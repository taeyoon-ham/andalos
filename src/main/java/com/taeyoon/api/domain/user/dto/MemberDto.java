package com.taeyoon.api.domain.user.dto;

import com.taeyoon.api.domain.user.model.enumclass.EnumMemberStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true) // 부모속성까지 출력
public class MemberDto extends UserDto {
	private EnumMemberStatus statusCode;
}
