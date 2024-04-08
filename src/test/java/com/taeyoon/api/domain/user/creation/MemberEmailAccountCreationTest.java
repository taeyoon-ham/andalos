package com.taeyoon.api.domain.user.creation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.i18n.LocaleContextHolder;

import com.taeyoon.api.application.exception.EmailDuplicationException;
import com.taeyoon.api.domain.user.dto.AccountDto;
import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.domain.user.model.MemberEntity;
import com.taeyoon.api.domain.user.model.enumclass.EnumAccountProvider;
import com.taeyoon.api.infra.constants.MsgConsts;
import com.taeyoon.api.infra.constants.MsgUtils;
import com.taeyoon.api.infra.exception.client.InvalidArgumentException;
import com.taeyoon.api.infra.persistence.AccountRepository;
import com.taeyoon.api.infra.persistence.AdminRepository;
import com.taeyoon.api.infra.persistence.MemberRepository;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class MemberEmailAccountCreationTest {

	@Mock
	MemberRepository memberRepository;
	@Mock
	AdminRepository adminRepository;
	@Mock
	AccountRepository accountRepository;

	@Mock
	ModelMapper modelMapper;

	static MockedStatic<LocaleContextHolder> localUtils = mockStatic(LocaleContextHolder.class);
	static MockedStatic<MsgUtils> messageUtils = mockStatic(MsgUtils.class);

	static UserDto userDto;
	static AccountDto accountDto;

	@AfterAll
	public static void afterAll() {
		localUtils.close();
		messageUtils.close();
	}

	@BeforeAll
	static void init() {
		userDto = MemberDto.builder()
			.email("example@gmail.com")
			.countryCode("+82")
			.telNo("01035233696")
			.lastName("홍")
			.build();
		accountDto = AccountDto.builder()
			.loginId("example@gmail.com")
			.password("12345")
			.provider(EnumAccountProvider.EMAIL.toString())
			.build();
		userDto.setAccountDto(accountDto);
	}

	@Test
	@Order(1)
	@DisplayName("파라메터 유효청 체크 - firstName 에러발생")
	void validation() {
		String willErrorMessage = MsgConsts.ERROR_NOT_BLANK + " [firstName]";
		given(LocaleContextHolder.getLocale()).willReturn(Locale.KOREA);
		given(MsgUtils.getMessage(anyString(), anyString())).willReturn(willErrorMessage);
		UserRepositoryHelper userRepositoryHelper = new UserRepositoryHelper(memberRepository, adminRepository,
			accountRepository, modelMapper);
		UserCreation userCreation = new MemberEmailAccountCreation(userRepositoryHelper);
		InvalidArgumentException exception = assertThrows(InvalidArgumentException.class,
			() -> userCreation.create(userDto));
		assertEquals(willErrorMessage, exception.getMessage() + " " + exception.getMsgArgs()[0]);
	}

	@Test
	@Order(2)
	@DisplayName("이메일중복 체크")
	void emailDuplication() {
		userDto.setFirstName("길동");
		UserRepositoryHelper userRepositoryHelper = new UserRepositoryHelper(memberRepository, adminRepository,
			accountRepository, modelMapper);
		when(userRepositoryHelper.getMemberRepository().findByEmail(userDto.getEmail())).thenReturn(
			Optional.of(MemberEntity.builder().email(userDto.getEmail()).build()));
		UserCreation userCreation = new MemberEmailAccountCreation(userRepositoryHelper);
		EmailDuplicationException exception = assertThrows(EmailDuplicationException.class,
			() -> userCreation.create(userDto));
		assertEquals(MsgConsts.ERROR_EMAIL_DUPLICATED, exception.getMessage());
	}

	@Test
	@Order(3)
	@DisplayName("정상처리")
	void create() {
		userDto.setFirstName("길동");
		UserRepositoryHelper userRepositoryHelper = new UserRepositoryHelper(memberRepository, adminRepository,
			accountRepository, modelMapper);
		when(userRepositoryHelper.getMemberRepository().save(any())).thenReturn(MemberEntity.builder()
			.id(1L)
			.userTypeCode("TB_MEMBER")
			.build());
		UserCreation userCreation = new MemberEmailAccountCreation(userRepositoryHelper);
		userCreation.create(userDto);
	}
}