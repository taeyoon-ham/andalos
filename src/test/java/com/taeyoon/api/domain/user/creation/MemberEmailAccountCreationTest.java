package com.taeyoon.api.domain.user.creation;

import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.dto.UserDto;
import com.taeyoon.api.infra.constants.MessageConstants;
import com.taeyoon.api.infra.persistence.AdminRepository;
import com.taeyoon.api.infra.persistence.MemberRepository;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;
import com.taeyoon.api.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class MemberEmailAccountCreationTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    AdminRepository adminRepository;
    static MockedStatic<Locale> localUtils = mockStatic(Locale.class);
    static MockedStatic<MessageUtils> messageUtils = mockStatic(MessageUtils.class);

    static UserDto dto;

    @AfterAll
    public static void afterAll(){
        localUtils.close();
        messageUtils.close();
    }

    @BeforeAll
    static void init() {
        dto = MemberDto.builder()
                .email("example@gmail.com")
                .countryCode("+82")
                .telNo("01035233696")
                .lastName("홍")
                .build();
    }

    @Test
    void validation() {
        String willErrorMessage = MessageConstants.ERROR_NOT_BLANK + " [firstName]";
        given(Locale.getDefault()).willReturn(Locale.KOREA);
        given(MessageUtils.getMessage(anyString(), anyString())).willReturn(willErrorMessage);
        UserRepositoryHelper userRepositoryHelper = new UserRepositoryHelper(memberRepository, adminRepository);
        UserCreationFactory userCreationFactory = new MemberEmailAccountCreation(userRepositoryHelper);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userCreationFactory.create(dto));
        assertEquals(willErrorMessage, exception.getMessage());
    }

    @Test
    void create() {
        dto.setFirstName("길동");
        UserRepositoryHelper userRepositoryHelper = new UserRepositoryHelper(memberRepository, adminRepository);
        UserCreationFactory userCreationFactory = new MemberEmailAccountCreation(userRepositoryHelper);
        userCreationFactory.create(dto);
    }
}