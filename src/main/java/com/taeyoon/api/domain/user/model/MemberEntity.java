package com.taeyoon.api.domain.user.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_MEMBER")
@DiscriminatorValue("TB_MEMBER")
public class MemberEntity extends UserEntity {
    @Column(name = "STATUS_CODE")
    @Enumerated(EnumType.STRING)
    private MemberStatus statusCode;
}
