package com.coderhari.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coderhari.entitie.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email= :email")
    public User getUsetByUserName(@Param("email") String email);
}
