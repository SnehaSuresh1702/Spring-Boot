package com.assess1.tracker.service;

import com.assess1.tracker.entity.User;
import com.assess1.tracker.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    String createUser(User user);

    List<User> viewAllUser();

    User viewUser(String email_Id, Integer user_id) throws ResourceNotFoundException;

    String updateUser(User user , int user_id) throws ResourceNotFoundException;

    String deleteUser(int user_id);
}
