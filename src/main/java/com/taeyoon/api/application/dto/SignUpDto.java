package com.taeyoon.api.application.dto;

import java.io.Serializable;
import java.util.Date;

import com.taeyoon.api.domain.user.model.enumclass.EnumMemberStatus;
import com.taeyoon.api.infra.constants.MsgConsts;

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
	public static class SignUpReq implements Serializable {
		@Schema(description = "계정제공자", requiredMode = Schema.RequiredMode.REQUIRED, example = "EMAIL, KAKAO")
		@NotBlank(message = MsgConsts.ERROR_NOT_BLANK)
		private String provider;
		@Schema(description = "로그인ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "example@gmail.com")
		@NotBlank(message = MsgConsts.ERROR_NOT_BLANK)
		private String loginId;
		@Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "12343")
		@NotBlank(message = MsgConsts.ERROR_NOT_BLANK)
		private String password;

		@Schema(description = "이름", requiredMode = Schema.RequiredMode.REQUIRED, example = "길동")
		@NotBlank(message = MsgConsts.ERROR_NOT_BLANK)
		private String firstName;
		@Schema(description = "성", requiredMode = Schema.RequiredMode.REQUIRED, example = "홍")
		@NotBlank(message = MsgConsts.ERROR_NOT_BLANK)
		private String lastName;
		@Schema(description = "국가코드", requiredMode = Schema.RequiredMode.REQUIRED, example = "+82")
		@NotBlank(message = MsgConsts.ERROR_NOT_BLANK)
		private String countryCode;
		@Schema(description = "전화번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "01011112222")
		@NotBlank(message = MsgConsts.ERROR_NOT_BLANK)
		private String telNo;
	}

	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@Schema(title = "회원가입 요청")
	public static class SignUpRes implements Serializable {

		@Schema(description = "유저ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
		private Long userId;

		@Schema(description = "이름", requiredMode = Schema.RequiredMode.REQUIRED, example = "길동")
		private String firstName;
		@Schema(description = "성", requiredMode = Schema.RequiredMode.REQUIRED, example = "홍")
		private String lastName;
		@Schema(description = "국가코드", requiredMode = Schema.RequiredMode.REQUIRED, example = "+82")
		private String countryCode;
		@Schema(description = "전화번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "01011112222")
		private String telNo;
		@Schema(description = "가입일시", requiredMode = Schema.RequiredMode.REQUIRED, example = "01011112222")
		private Date regDate;
		@Schema(description = "상태코드", requiredMode = Schema.RequiredMode.REQUIRED, example = "APPROVED")
		private EnumMemberStatus statusCode;

		@Schema(description = "계정정보", requiredMode = Schema.RequiredMode.REQUIRED)
		private AccountRes account;
	}

	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@Schema(title = "계정정보")
	public static class AccountRes implements Serializable {

		@Schema(description = "계정ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
		private Long accountId;
		@Schema(description = "계정제공자", requiredMode = Schema.RequiredMode.REQUIRED, example = "EMAIL, KAKAO")
		private String provider;
		@Schema(description = "로그인ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "example@gmail.com")
		private String loginId;
	}
}
