package com.taeyoon.api.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taeyoon.api.application.dto.SignUpDto;
import com.taeyoon.api.domain.user.creation.MemberEmailAccountCreation;
import com.taeyoon.api.domain.user.creation.MemberGoogleAccountCreation;
import com.taeyoon.api.domain.user.creation.MemberKakaoAccountCreation;
import com.taeyoon.api.domain.user.creation.UserCreationFactory;
import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.model.enumclass.EnumAccountProvider;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
class UserServiceImpl implements UserService, UserDetailsService {
	private final UserRepositoryHelper userRepositoryHelper;

	@Override
	public void create(SignUpDto.ReqSignUp req) {
		UserCreationFactory userCreationFactory;

		EnumAccountProvider accountProvider = EnumAccountProvider.valueOf(req.getProvider());
		switch (accountProvider) {
			case EnumAccountProvider.KAKAO ->
				userCreationFactory = new MemberKakaoAccountCreation(userRepositoryHelper);
			case EnumAccountProvider.GOOGLE ->
				userCreationFactory = new MemberGoogleAccountCreation(userRepositoryHelper);
			default -> userCreationFactory = new MemberEmailAccountCreation(userRepositoryHelper);
		}

		userCreationFactory.create(MemberDto.builder().build());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return MemberDto.builder().build();
	}
}
