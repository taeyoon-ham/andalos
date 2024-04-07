package com.taeyoon.api.domain.user.finding;

import com.taeyoon.api.domain.user.model.MemberEntity;
import com.taeyoon.api.infra.constants.MessageConstants;
import com.taeyoon.api.infra.exception.client.NotFoundException;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;
import com.taeyoon.api.utils.MessageUtils;

public class UserFindingImpl implements UserFinding {
	private final UserRepositoryHelper userRepositoryHelper;

	public UserFindingImpl(UserRepositoryHelper userRepositoryHelper) {
		this.userRepositoryHelper = userRepositoryHelper;
	}

	@Override
	public MemberEntity findMemberByEmail(String email) {
		return userRepositoryHelper.getMemberRepository()
			.findByEmail(email)
			.orElseThrow(() -> new NotFoundException(MessageUtils.getMessage(MessageConstants.ERROR_DATA_NOT_FOUND)));
	}
}
