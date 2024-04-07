package com.taeyoon.api.infra.exception.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@Schema(title = "에러 공통")
public class ErrorResponse implements Serializable {
    @Schema(description = "에러발생시간", example = "2024-04-06T17:17:37.490+00:00")
    private Date timestamp;
    @Schema(description = "Http status code", example = "404")
    private int status;
    @Schema(description = "exception name", example = "Not Found")
    private String error;
    @Schema(description = "기 정의된 에러코드", example = "Not Found")
    private String errorCode;
    @Schema(description = "메세지", example = "No static resource api/service/v1/sign-out2.")
    private String message;
    @Schema(description = "uri", example = "BadRequestException")
    private String path;
}
