package com.taeyoon.api.application.service;

import com.taeyoon.api.application.dto.SignUpDto;

public interface UserService {
	SignUpDto.ResSignUp create(SignUpDto.ReqSignUp req);
}
