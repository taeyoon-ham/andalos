package com.taeyoon.api.component.user;

import com.taeyoon.api.domain.user.model.MemberEntity;
import com.taeyoon.api.domain.user.model.MemberStatus;
import com.taeyoon.api.infra.persistence.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    void save() {
        MemberEntity memberEntity = memberRepository.save(MemberEntity.builder()
                        .statusCode(MemberStatus.APPROVED)
                        .telNo("01035233696")
                        .email("mason8.ham@gmail.com")
                        .countryCode("+81")
                        .lastName("함")
                        .firstName("장수")
                .build());

        log.info("memberEntity.id={}", memberEntity.getId());
    }

}