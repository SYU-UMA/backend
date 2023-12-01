package com.jobayour.jwt;

import com.jobayour.modules.user.UserDTO;

public interface JwtUserService {
    UserDTO.User registerUser(UserDTO.User user);
    String loginUser(UserDTO.User user);
}