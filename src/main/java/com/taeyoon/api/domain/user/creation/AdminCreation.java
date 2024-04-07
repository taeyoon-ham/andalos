package com.taeyoon.api.domain.user.creation;

import com.taeyoon.api.domain.user.dto.AdminDto;
import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.domain.user.model.AdminStatus;
import com.taeyoon.api.domain.user.model.AdminEntity;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

public class AdminCreation extends DefaultUserCreationFactory {
    public AdminCreation(UserRepositoryHelper userRepositoryHelper) {
        super(userRepositoryHelper);
    }

    @Override
    public UserDto create() {
        AdminEntity adminEntity = repository.getAdminRepository().save(AdminEntity.builder()
                .statusCode(AdminStatus.APPROVED)
                .telNo("01035233696")
                .email("mason8.ham@gmail.com")
                .countryCode("+81")
                .lastName("함")
                .firstName("장수")
                .build());
        repository.getAdminRepository().save(adminEntity);
        return modelMapper.map(adminEntity, AdminDto.class);
    }
}
