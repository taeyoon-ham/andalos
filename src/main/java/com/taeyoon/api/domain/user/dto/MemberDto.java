package com.taeyoon.api.domain.user.dto;


import com.taeyoon.api.domain.user.model.enumclass.EnumMemberStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true) // 부모속성까지 출력
public class MemberDto extends UserDto {
    private EnumMemberStatus statusCode;
}
