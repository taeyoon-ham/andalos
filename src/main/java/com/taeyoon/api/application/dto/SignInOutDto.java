package com.taeyoon.api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

public class SignInOutDto implements Serializable {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Schema(title = "로그인 요청")
    public static class ReqSignIn implements Serializable {

        @Schema(description = "로그인ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "example@gmail.com")
        private String loginId;

        @Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "12343")
        private String password;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Schema(title = "로그인 응답")
    public static class ResSignIn implements Serializable {

        @Schema(description = "로그인ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "example@gmail.com")
        private String loginId;

        @Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "12343")
        private String password;
    }
}
