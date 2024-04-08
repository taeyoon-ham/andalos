package com.taeyoon.api.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taeyoon.api.application.dto.SignUpDto;
import com.taeyoon.api.domain.user.creation.UserCreation;
import com.taeyoon.api.domain.user.creation.UserCreationFacade;
import com.taeyoon.api.domain.user.creation.UserCreationFacadeImpl;
import com.taeyoon.api.domain.user.dto.AccountDto;
import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
class UserServiceImpl implements UserService, UserDetailsService {
	private final UserRepositoryHelper userRepositoryHelper;

	@Override
	public SignUpDto.SignUpRes create(SignUpDto.SignUpReq req) {
		UserCreationFacade userFacade = new UserCreationFacadeImpl(userRepositoryHelper);
		UserCreation userCreation = userFacade.getUserCreation(req.getProvider());
		MemberDto memberDto = userRepositoryHelper.getModelMapper().map(req, MemberDto.class);
		AccountDto accountDto = userRepositoryHelper.getModelMapper().map(req, AccountDto.class);
		memberDto.setEmail(req.getLoginId());
		memberDto.setAccountDto(accountDto);
		userCreation.create(memberDto);

		SignUpDto.SignUpRes res = userRepositoryHelper.getModelMapper().map(memberDto, SignUpDto.SignUpRes.class);
		res.setUserId(memberDto.getId());
		SignUpDto.AccountRes resAccount = userRepositoryHelper.getModelMapper()
			.map(memberDto.getAccountDto(), SignUpDto.AccountRes.class);
		resAccount.setAccountId(memberDto.getAccountDto().getId());
		res.setAccount(resAccount);
		return res;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return MemberDto.builder().id(1L).build();
	}
}
