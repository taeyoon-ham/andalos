package com.taeyoon.api.application.service;

import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.domain.user.creation.MemberCreation;
import com.taeyoon.api.domain.user.creation.UserCreationFactory;
import com.taeyoon.api.infra.persistence.UserRepositoryHelper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepositoryHelper userRepositoryHelper;
    @Override
    public void create() {
        UserCreationFactory userCreationFactory = new MemberCreation(userRepositoryHelper);
        userCreationFactory.create();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return MemberDto.builder().build();
    }
}
