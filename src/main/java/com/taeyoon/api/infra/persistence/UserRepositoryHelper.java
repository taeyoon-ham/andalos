package com.taeyoon.api.infra.persistence;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@Getter
@AllArgsConstructor
public class UserRepositoryHelper {
	private final MemberRepository memberRepository;
	private final AdminRepository adminRepository;
	private final ModelMapper modelMapper;
}
