package com.taeyoon.api.application.service;

import com.taeyoon.api.application.dto.SignUpDto;

public interface UserService {
	SignUpDto.SignUpRes create(SignUpDto.SignUpReq req);
}
