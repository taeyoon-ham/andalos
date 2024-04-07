package com.taeyoon.api.domain.user.creation;

import java.time.LocalDateTime;
import java.util.Optional;

import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.domain.user.model.MemberEntity;
import com.taeyoon.api.domain.user.model.enumclass.EnumMemberStatus;
import com.taeyoon.api.infra.exception.client.EmailDuplicationException;
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
		Optional<MemberEntity> memberOptional = repositoryHelper.getMemberRepository().findByEmail(dto.getEmail());
		if (memberOptional.isPresent()) {
			throw new EmailDuplicationException();
		}

		log.info("dto, {}", dto);
		MemberDto memberDto = (MemberDto)dto;
		MemberEntity willSaveMember = repositoryHelper.getModelMapper().map(memberDto, MemberEntity.class);
		willSaveMember.setStatusCode(EnumMemberStatus.APPROVED);
		willSaveMember.setRegDate(LocalDateTime.now());
		willSaveMember.setDelYn("N");

		MemberEntity memberEntity = repositoryHelper.getMemberRepository().save(willSaveMember);
		return repositoryHelper.getModelMapper().map(memberEntity, MemberDto.class);
	}

}
