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
@Table(name = "TB_USER")
@Inheritance(strategy = InheritanceType.JOINED) // 상속 구현 전략 선택
@DiscriminatorColumn(name = "USER_TYPE_CODE")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "COUNTRY_CODE")
    private String countryCode;
    @Column(name = "TEL_NO")
    private String telNo;

}
