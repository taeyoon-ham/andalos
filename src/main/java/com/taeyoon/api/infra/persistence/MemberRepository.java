package com.taeyoon.api.infra.persistence;

import com.taeyoon.api.domain.user.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
