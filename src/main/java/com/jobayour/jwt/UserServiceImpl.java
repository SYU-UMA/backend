package com.jobayour.jwt;

import com.jobayour.modules.user.User;
import com.jobayour.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements JwtUserService {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void logoutUser(String userId) {
        String refreshToken = jwtTokenProvider.getRefreshToken(userId);
        if (refreshToken != null) {
            // 리프레시 토큰이 존재하면 폐기
            jwtTokenProvider.deleteRefreshToken(userId); // 변경된 부분
        }
        redisTemplate.delete(userId); // 변경된 부분
    }
    @Override
    public User registerUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            //동일 ID일 시
            throw new RuntimeException("이미 등록된 사용자입니다.");
        }

        return userRepository.save(user);
    }

    @Override
    public String loginUser(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserId());
        if (!userDetails.getPassword().equals(user.getUserPwd())) {
            //비밀번호가 일치하지 않을 시
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.generateAccessToken(userDetails.getUsername());
    }


    @Override
    public Map<String, Object> getUserInfo(String userId) {
        User user = userRepository.findUserByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(null);
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userId", user.getUserId());
            userInfo.put("userName", user.getUserName());
            userInfo.put("userBirthday", user.getUserBirthday());
            userInfo.put("userPhone", user.getUserPhone());
            userInfo.put("userEmail", user.getUserEmail());
            return userInfo;

    }



}
