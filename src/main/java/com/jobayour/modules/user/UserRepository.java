package com.jobayour.modules.user;
//수정
import com.jobayour.modules.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDTO.User, String> {

    //@Query("select u from User u where u.id = ?1 ")
    List<UserDTO.User> findUserByUserId(String id);
}
