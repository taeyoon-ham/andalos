package com.taeyoon.api.domain.user.creation;

import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberEmailAccountCreation extends DefaultUserCreationFactory {
	public MemberEmailAccountCreation(UserRepositoryHelper userRepositoryHelper) {
		super(userRepositoryHelper);
	}

	@Override
	public UserDto create(UserDto dto) {
		userDto = dto;
		// 필수입력항목 유효성 체크
		log.info("필수항목 체크");
		this.validation();

		// user pk 중복 체크 (email)
		log.info("이메일 중복 체크");

		log.info("dto, {}", dto.toString());

		//        MemberEntity memberEntity = repository.getMemberRepository().save(MemberEntity.builder()
		//                .statusCode(EnumMemberStatus.APPROVED)
		//                .telNo("01035233696")
		//                .email("mason8.ham@gmail.com")
		//                .countryCode("+81")
		//                .lastName("함")
		//                .firstName("장수")
		//                .build());
		//        repository.getMemberRepository().save(memberEntity);
		return MemberDto.builder().build();
	}

}
