package com.jobayour.jwt;

import com.jobayour.modules.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class JwtUserController {

    @Autowired
    private JwtUserService jwtuserService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return jwtuserService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return jwtuserService.loginUser(user);
    }

/*
    @PostMapping("/logout2")
    public ResponseEntity<String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        jwtuserService.logoutUser(userId);
        return new ResponseEntity<>("로그아웃", HttpStatus.OK);
    }*/
    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            jwtTokenProvider.deleteRefreshToken(jwtTokenProvider.getUserId(token));
            jwtTokenProvider.addToBlacklist(token);
            redisTemplate.delete(jwtTokenProvider.getUserId(token));
      return new ResponseEntity<>("로그아웃", HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findUserId")
    public ResponseEntity<String> findUserId(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);
        if (userId != null){
            return new ResponseEntity<>(userId, HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getUserInfo(HttpServletRequest request) {
        try {
            // JWT 토큰 추출
            String token = jwtTokenProvider.resolveToken(request);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                String userId = jwtTokenProvider.getUserId(token);
                Map<String, Object> userInfo = jwtuserService.getUserInfo(userId);
                return new ResponseEntity<>(userInfo, HttpStatus.OK);
            } else {
                // 토큰이 유효하지 않은 경우
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modifyUser(HttpServletRequest request, @RequestBody Map<String, String> updateData ){
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)){
            String userId = jwtTokenProvider.getUserId(token);
            jwtuserService.modifyUser(userId, updateData);
            return new ResponseEntity<>("수정완료", HttpStatus.OK);
        }   return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }


}
