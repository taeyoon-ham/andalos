package com.taeyoon.api.domain.user.creation;

import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.domain.user.model.MemberEntity;
import com.taeyoon.api.domain.user.model.MemberStatus;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

public class MemberCreation extends DefaultUserCreationFactory {
    public MemberCreation(UserRepositoryHelper userRepositoryHelper) {
        super(userRepositoryHelper);
    }

    @Override
    public UserDto create() {
        MemberEntity memberEntity = repository.getMemberRepository().save(MemberEntity.builder()
                .statusCode(MemberStatus.APPROVED)
                .telNo("01035233696")
                .email("mason8.ham@gmail.com")
                .countryCode("+81")
                .lastName("함")
                .firstName("장수")
                .build());
        repository.getMemberRepository().save(memberEntity);
        return modelMapper.map(memberEntity, MemberDto.class);
    }
}
