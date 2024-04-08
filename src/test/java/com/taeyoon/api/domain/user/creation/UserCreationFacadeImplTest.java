package com.taeyoon.api.domain.user.creation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.taeyoon.api.domain.user.model.enumclass.EnumAccountProvider;
import com.taeyoon.api.infra.persistence.AccountRepository;
import com.taeyoon.api.infra.persistence.AdminRepository;
import com.taeyoon.api.infra.persistence.MemberRepository;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserCreationFacadeImplTest {

	@Mock
	MemberRepository memberRepository;
	@Mock
	AdminRepository adminRepository;
	@Mock
	AccountRepository accountRepository;

	@Mock
	ModelMapper modelMapper;

	@Test
	@Order(1)
	@DisplayName("회원이메일계정 객체 확인 - 이메일")
	void returnMemberEmailAccountCreation() {
		UserRepositoryHelper repositoryHelper = new UserRepositoryHelper(memberRepository, adminRepository,
			accountRepository, modelMapper);
		UserCreationFacade userCreationFacade = new UserCreationFacadeImpl(repositoryHelper);
		UserCreation userCreation = userCreationFacade.getUserCreation(EnumAccountProvider.EMAIL.toString());
		assertInstanceOf(MemberEmailAccountCreation.class, userCreation);
	}

	@Test
	@Order(2)
	@DisplayName("회원이메일계정 객체 확인 - 구글")
	void returnMemberGoogleAccountCreation() {
		UserRepositoryHelper repositoryHelper = new UserRepositoryHelper(memberRepository, adminRepository,
			accountRepository, modelMapper);
		UserCreationFacade userCreationFacade = new UserCreationFacadeImpl(repositoryHelper);
		UserCreation userCreation = userCreationFacade.getUserCreation(EnumAccountProvider.GOOGLE.toString());
		assertInstanceOf(MemberGoogleAccountCreation.class, userCreation);
	}

	@Test
	@Order(3)
	@DisplayName("회원이메일계정 객체 확인 - 카카오")
	void returnMemberKakaoAccountCreation() {
		UserRepositoryHelper repositoryHelper = new UserRepositoryHelper(memberRepository, adminRepository,
			accountRepository, modelMapper);
		UserCreationFacade userCreationFacade = new UserCreationFacadeImpl(repositoryHelper);
		UserCreation userCreation = userCreationFacade.getUserCreation(EnumAccountProvider.KAKAO.toString());
		assertInstanceOf(MemberKakaoAccountCreation.class, userCreation);
	}
}