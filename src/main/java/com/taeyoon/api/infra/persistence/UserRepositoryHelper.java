package com.taeyoon.api.infra.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
public class UserRepositoryHelper {
    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
}
