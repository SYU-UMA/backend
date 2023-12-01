package com.jobayour.jwt;

import com.jobayour.modules.user.User;

public interface JwtUserService {
    User registerUser(User user);
    String loginUser(User user);
}