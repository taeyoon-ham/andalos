package com.taeyoon.api.domain.user.creation;

import com.taeyoon.api.infra.persistence.UserRepositoryHelper;
import org.modelmapper.ModelMapper;

abstract class DefaultUserCreationFactory implements UserCreationFactory {
    protected final UserRepositoryHelper repository;
    protected final ModelMapper modelMapper = new ModelMapper();
    public DefaultUserCreationFactory(UserRepositoryHelper repository) {
        this.repository = repository;
    }
}
