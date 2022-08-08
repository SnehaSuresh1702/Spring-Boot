package com.assess1.tracker.repository;

import com.assess1.tracker.entity.User;
import com.assess1.tracker.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User , Integer>{

    @Query(value = "select * from user where email_Id=:email_Id",nativeQuery = true)
    User findByEmail(String email_Id) throws ResourceNotFoundException;
}