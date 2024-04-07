package com.taeyoon.api.domain.user.finding;

import com.taeyoon.api.domain.user.model.MemberEntity;

public interface UserFinding {
    MemberEntity findMemberByEmail(String email);
}
