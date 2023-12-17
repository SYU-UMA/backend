package com.jobayour.jwt;

import com.jobayour.modules.user.User;
import com.jobayour.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

        return generateAccessToken(userDetails.getUsername());
    }

    private String generateAccessToken(String userId) {
        return jwtTokenProvider.createToken(userId, Collections.emptyList()).get("accessToken");
    }
    @Override
    public Map<String, Object> getUserInfo(String userId) {
        User user = userRepository.findUserByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(null);
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userId", user.getUserId());
            userInfo.put("userPwd", user.getUserPwd());
            userInfo.put("userName", user.getUserName());
            userInfo.put("userBirthday", user.getUserBirthday());
            userInfo.put("userPhone", user.getUserPhone());
            userInfo.put("userEmail", user.getUserEmail());

            return userInfo;

    }

    @Override
    public Map<String, Object> modifyUser(String userId, Map<String, String> updateData) {
        User user = userRepository.findById(userId)
                .stream()
                .findFirst()
                .orElseThrow(null);

        // 제공된 데이터를 기반으로 사용자 정보를 업데이트합니다.
        if (updateData.containsKey("userName")) {
            user.setUserName(updateData.get("userName"));
        }
        if (updateData.containsKey("userBirthday")) {
            // userBirthday가 "yyyy-MM-dd" 형식으로 가정합니다.
            user.setUserBirthday(Date.valueOf(updateData.get("userBirthday")));
        }
        if (updateData.containsKey("userPhone")) {
            user.setUserPhone(updateData.get("userPhone"));
        }
        if (updateData.containsKey("userEmail")) {
            user.setUserEmail(updateData.get("userEmail"));
        }
        if (updateData.containsKey("userPwd")){
            user.setUserPwd(updateData.get("userPwd"));
        }

        // 업데이트된 사용자 정보를 데이터베이스에 저장합니다.
        userRepository.save(user);

        // 업데이트된 사용자 정보를 반환합니다.
        return getUserInfo(userId);
    }
}
