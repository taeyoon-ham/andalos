package com.taeyoon.api.application.dto;

import java.io.Serializable;

import com.taeyoon.api.infra.constants.MessageConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class SignUpDto implements Serializable {

	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@Schema(title = "회원가입 요청")
	public static class ReqSignUp implements Serializable {
		@Schema(description = "계정제공자", requiredMode = Schema.RequiredMode.REQUIRED, example = "EMAIL, KAKAO")
		@NotBlank(message = MessageConstants.ERROR_NOT_BLANK)
		private String provider;
		@Schema(description = "로그인ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "example@gmail.com")
		@NotBlank(message = MessageConstants.ERROR_NOT_BLANK)
		private String loginId;
		@Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "12343")
		@NotBlank(message = MessageConstants.ERROR_NOT_BLANK)
		private String password;

		@Schema(description = "이름", requiredMode = Schema.RequiredMode.REQUIRED, example = "길동")
		@NotBlank(message = MessageConstants.ERROR_NOT_BLANK)
		private String firstName;
		@Schema(description = "성", requiredMode = Schema.RequiredMode.REQUIRED, example = "홍")
		@NotBlank(message = MessageConstants.ERROR_NOT_BLANK)
		private String lastName;
		@Schema(description = "국가코드", requiredMode = Schema.RequiredMode.REQUIRED, example = "+82")
		@NotBlank(message = MessageConstants.ERROR_NOT_BLANK)
		private String countryCode;
		@Schema(description = "전화번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "01011112222")
		@NotBlank(message = MessageConstants.ERROR_NOT_BLANK)
		private String telNo;
	}
}
