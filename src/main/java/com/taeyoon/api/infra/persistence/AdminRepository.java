package com.taeyoon.api.infra.persistence;

import com.taeyoon.api.domain.user.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
