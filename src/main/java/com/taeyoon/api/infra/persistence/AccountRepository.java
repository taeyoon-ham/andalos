package com.taeyoon.api.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taeyoon.api.domain.user.model.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
