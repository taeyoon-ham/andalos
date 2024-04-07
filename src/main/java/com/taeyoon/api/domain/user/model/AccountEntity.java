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
@Table(name = "TB_ACCOUNT")
public class AccountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROVIDER")
    private String provider;
    @Column(name = "LOGIN_ID")
    private String loginId;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "USER_TYPE_CODE")
    private String userTypeCode;
    @Column(name = "USER_ID")
    private Long userId;

}
