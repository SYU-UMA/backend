package com.jobayour.repository;
//수정
import com.jobayour.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //@Query("select u from User u where u.id = ?1 ")
    List<User> findUserByUserId(String id);
}
