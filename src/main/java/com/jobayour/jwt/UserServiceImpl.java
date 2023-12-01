package com.jobayour.jwt;

import com.jobayour.modules.user.UserDTO;
import com.jobayour.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements JwtUserService {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;


    @Override
    public UserDTO.User registerUser(UserDTO.User user) {
        if (userRepository.existsById(user.getUserId())) {
            throw new RuntimeException("이미 등록된 사용자입니다.");
        }

        return userRepository.save(user);
    }

    @Override
    public String loginUser(UserDTO.User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserId());
        if (!userDetails.getPassword().equals(user.getUserPwd())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(userDetails.getUsername(), Collections.emptyList());
    }




}