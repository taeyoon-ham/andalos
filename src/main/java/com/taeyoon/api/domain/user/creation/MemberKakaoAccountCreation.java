package com.taeyoon.api.domain.user.creation;

import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.domain.user.model.MemberEntity;
import com.taeyoon.api.domain.user.model.enumclass.EnumMemberStatus;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

public class MemberKakaoAccountCreation extends DefaultUserCreationFactory {
	public MemberKakaoAccountCreation(UserRepositoryHelper userRepositoryHelper) {
		super(userRepositoryHelper);
	}

	@Override
	public UserDto create(UserDto dto) {

		// 필수입력항목 유효성 체크

		// user pk 중복 체크 (email)

		MemberEntity memberEntity = repositoryHelper.getMemberRepository().save(MemberEntity.builder()
			.statusCode(EnumMemberStatus.APPROVED)
			.telNo("01035233696")
			.email("mason8.ham@gmail.com")
			.countryCode("+81")
			.lastName("함")
			.firstName("장수")
			.build());
		repositoryHelper.getMemberRepository().save(memberEntity);
		return modelMapper.map(memberEntity, MemberDto.class);
	}
}
