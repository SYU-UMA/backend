package com.jobayour.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

// 토큰을 생성하고 검증하는 클래스입니다.
// 해당 컴포넌트는 필터클래스에서 사전 검증을 거칩니다.
@Component
public class JwtTokenProvider {
    private String secretKey = "jobayourkey";
    private final UserDetailsService userDetailsService;
    private long tokenValidTime = 30 * 60 * 1000L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 생성자 주입을 통한 UserDetailsService 설정
    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String refreshTokenKey(String userId){
        return "refreshToken:" + userId;
    }
    //레디스에 리프레쉬 토큰 저장
    private void saveRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForValue().set(refreshTokenKey(userId), refreshToken, tokenValidTime * 2, TimeUnit.MILLISECONDS);
    }
    // 레디스에서 리프레쉬토큰
    public String getRefreshToken(String userId) {
        return (String) redisTemplate.opsForValue().get(refreshTokenKey(userId));
    }
    // JWT 토큰 생성
    public Map<String, String> createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(now.getTime() + tokenValidTime * 2))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        saveRefreshToken(userId, refreshToken);

        Map<String, String> token = new HashMap<>();
        token.put("accessToken", accessToken);
        token.put("refreshToken", refreshToken);

        return token;
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    //리프레쉬 토큰 삭제
    public void deleteRefreshToken(String userId) {
        redisTemplate.delete(refreshTokenKey(userId));
    }
    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}