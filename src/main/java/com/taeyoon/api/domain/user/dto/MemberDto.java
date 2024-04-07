package com.taeyoon.api.domain.user.dto;


import com.taeyoon.api.domain.user.model.MemberStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class MemberDto extends UserDto {
    private MemberStatus statusCode;
}
