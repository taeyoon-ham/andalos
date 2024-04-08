package com.taeyoon.api.domain.user.creation;

import java.util.Date;
import java.util.Optional;

import com.taeyoon.api.application.exception.EmailDuplicationException;
import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.domain.user.model.AccountEntity;
import com.taeyoon.api.domain.user.model.MemberEntity;
import com.taeyoon.api.domain.user.model.enumclass.EnumMemberStatus;
import com.taeyoon.api.infra.constants.MsgConsts;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class MemberEmailAccountCreation extends DefaultUserCreation {
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
		Optional<MemberEntity> memberOptional = repositoryHelper.getMemberRepository()
			.findByEmail(dto.getEmail());
		if (memberOptional.isPresent()) {
			throw new EmailDuplicationException(MsgConsts.ERROR_EMAIL_DUPLICATED);
		}

		// todo 전화번호 중복 체크도 해야 함.

		MemberDto memberDto = (MemberDto)userDto;
		MemberEntity memberEntity = MemberEntity.builder()
			.email(memberDto.getEmail())
			.countryCode(memberDto.getCountryCode())
			.firstName(memberDto.getFirstName())
			.lastName(memberDto.getLastName())
			.telNo(memberDto.getTelNo())
			.statusCode(EnumMemberStatus.APPROVED)
			.regDate(new Date())
			.delYn("N")
			.build();
		repositoryHelper.getMemberRepository().save(memberEntity);
		AccountEntity accountEntity = AccountEntity.builder()
			.provider(memberDto.getAccountDto().getProvider())
			.loginId(memberDto.getEmail())
			.password(memberDto.getAccountDto().getPassword())
			.userId(memberEntity.getId())
			.userTypeCode("TB_MEMBER")
			.delYn("N")
			.build();
		repositoryHelper.getAccountRepository().save(accountEntity);
		memberEntity.setAccountId(accountEntity.getId());
		memberDto.setId(memberEntity.getId());
		memberDto.getAccountDto().setId(accountEntity.getId());
		memberDto.getAccountDto().setLoginId(memberEntity.getEmail());
		memberDto.getAccountDto().setPassword(null);
		memberDto.getAccountDto().setUserId(memberEntity.getId());
		memberDto.getAccountDto().setUserTypeCode(memberEntity.getUserTypeCode());
		memberDto.setStatusCode(memberEntity.getStatusCode());
		memberDto.setRegDate(memberEntity.getRegDate());
		return memberDto;
	}

}
