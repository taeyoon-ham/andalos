package com.taeyoon.api.domain.user.creation;

import org.modelmapper.ModelMapper;

import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.infra.constants.MessageConstants;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;
import com.taeyoon.api.utils.MessageUtils;

import io.jsonwebtoken.lang.Assert;

abstract class DefaultUserCreationFactory implements UserCreationFactory {
	protected final UserRepositoryHelper repository;
	protected final ModelMapper modelMapper = new ModelMapper();
	protected UserDto userDto;

	public DefaultUserCreationFactory(UserRepositoryHelper repository) {
		this.repository = repository;
	}

	protected void validation() {
		Assert.notNull(userDto.getEmail(), MessageUtils.getMessage(MessageConstants.ERROR_NOT_BLANK, "[email]"));
		Assert.notNull(userDto.getFirstName(),
			MessageUtils.getMessage(MessageConstants.ERROR_NOT_BLANK, "[firstName]"));
		Assert.notNull(userDto.getLastName(), MessageUtils.getMessage(MessageConstants.ERROR_NOT_BLANK, "[lastName]"));
		Assert.notNull(userDto.getTelNo(), MessageUtils.getMessage(MessageConstants.ERROR_NOT_BLANK, "[email]"));
	}
}
