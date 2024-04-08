package com.taeyoon.api.domain.user.creation;

import org.modelmapper.ModelMapper;

import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.infra.constants.MsgConsts;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;
import com.taeyoon.api.utils.CustomAssert;

abstract class DefaultUserCreation implements UserCreation {
	protected final UserRepositoryHelper repositoryHelper;
	protected final ModelMapper modelMapper = new ModelMapper();
	protected UserDto userDto;

	public DefaultUserCreation(UserRepositoryHelper repositoryHelper) {
		this.repositoryHelper = repositoryHelper;
	}

	protected void validation() {
		CustomAssert.notNull(userDto.getEmail(), MsgConsts.ERROR_NOT_BLANK, "[email]");
		CustomAssert.notNull(userDto.getFirstName(), MsgConsts.ERROR_NOT_BLANK, "[firstName]");
		CustomAssert.notNull(userDto.getLastName(), MsgConsts.ERROR_NOT_BLANK, "[lastName]");
		CustomAssert.notNull(userDto.getTelNo(), MsgConsts.ERROR_NOT_BLANK, "[email]");
	}
}
