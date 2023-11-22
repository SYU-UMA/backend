package com.jobayour.jwt;

import com.jobayour.entity.User;

public interface JwtUserService {
    User registerUser(User user);
    String loginUser(User user);
}