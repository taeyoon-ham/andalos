package com.taeyoon.api.domain.user.creation;

import com.taeyoon.api.domain.user.model.enumclass.EnumAccountProvider;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

public class UserCreationFacadeImpl implements UserCreationFacade {
	private final UserRepositoryHelper repositoryHelper;

	public UserCreationFacadeImpl(UserRepositoryHelper repositoryHelper) {
		this.repositoryHelper = repositoryHelper;
	}

	@Override
	public UserCreation getUserCreation(String accountProvider) {
		EnumAccountProvider enumAccountProvider = EnumAccountProvider.valueOf(accountProvider);
		UserCreation userCreation;
		switch (enumAccountProvider) {
			case EnumAccountProvider.KAKAO -> userCreation = new MemberKakaoAccountCreation(repositoryHelper);
			case EnumAccountProvider.GOOGLE -> userCreation = new MemberGoogleAccountCreation(repositoryHelper);
			default -> userCreation = new MemberEmailAccountCreation(repositoryHelper);
		}
		return userCreation;
	}
}
