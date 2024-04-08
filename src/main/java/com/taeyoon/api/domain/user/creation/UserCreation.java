package com.taeyoon.api.domain.user.creation;

import com.taeyoon.api.domain.user.dto.UserDto;

public interface UserCreation {
	UserDto create(UserDto userDto);
}
