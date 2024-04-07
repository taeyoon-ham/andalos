package com.taeyoon.api.domain.user.model;

import com.taeyoon.api.domain.user.model.enumclass.EnumAdminStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ADMIN")
@DiscriminatorValue("TB_ADMIN")
public class AdminEntity extends UserEntity {
    @Column(name = "STATUS_CODE")
    @Enumerated(EnumType.STRING)
    private EnumAdminStatus statusCode;
}
