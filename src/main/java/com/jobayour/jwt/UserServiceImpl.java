package com.jobayour.jwt;

import com.jobayour.entity.User;
import com.jobayour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements JwtUserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public User registerUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            throw new RuntimeException("이미 등록된 사용자입니다.");
        }

        return userRepository.save(user);
    }

    @Override
    public String loginUser(User user) {
        UserDetails userDetails = loadUserByUsername(user.getUserId());
        if (!userDetails.getPassword().equals(user.getUserPwd())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(userDetails.getUsername(), Collections.emptyList());
    }

    private UserDetails loadUserByUsername(String username) {
        List<User> userList = userRepository.findUserByUserId(username);

        User user = userList.stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getUserPwd(),
                Collections.emptyList()
        );
    }
}