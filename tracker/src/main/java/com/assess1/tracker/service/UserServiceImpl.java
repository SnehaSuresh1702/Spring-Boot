package com.assess1.tracker.service;

import com.assess1.tracker.entity.User;
import com.assess1.tracker.exception.ResourceNotFoundException;
import com.assess1.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUser(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail_Id(user.getEmail_Id());

        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            return ("Duplicate emailID. Please enter a unique emailID");
        }

        return "User with " + newUser.getEmail_Id() + " created successfully" + " and URL "
                + "http://localhost:8080/viewUser?email_Id=&user_id=" + Integer.toString(newUser.getUser_id());
    }

    @Override
    public List<User> viewAllUser() {

        return userRepository.findAll();
    }

    @Override
    public User viewUser(String email_Id, Integer user_id) throws ResourceNotFoundException {

        List<User> userList = userRepository.findAll();
        User userfind = null;

        if (user_id != null && email_Id.length() != 0) {
            System.out.println(email_Id);
            System.out.println(user_id);
            for (User user : userList) {
                if ((user.getEmail_Id().equals(email_Id)) &&
                        (user.getUser_id() == user_id)) {
                    return user;
                }
            }throw new ResourceNotFoundException("No user found");
        }
        else if (user_id == null && email_Id.length() == 0)
            throw new ResourceNotFoundException("Please enter the inputs");

        else if (user_id != null) {
            return userRepository.findById(user_id)
                    .orElseThrow(() -> new ResourceNotFoundException("No user found"));
        } else if (email_Id.length() != 0) {
            userfind = userRepository.findByEmail(email_Id);
            if (userfind != null)
                return userRepository.findByEmail(email_Id);
            else
                throw new ResourceNotFoundException("No user found");
        }
        return userfind;
    }

    public String updateUser(User user, int user_id) throws ResourceNotFoundException {
        Optional<User> byId = userRepository.findById(user_id);
        User users = null;
        if (byId.isPresent()) {
            users = byId.get();
        } else {
            throw new ResourceNotFoundException("User with user_id " + user_id + " not found");
        }

        users.setName(user.getName());
        users.getEmail_Id();
        users.setLast_modified_datetime(LocalDateTime.now());
        userRepository.save(users);
        return "Successfully updated the User with user_id " + user_id;
    }

    public String deleteUser(int user_id) {
        Optional<User> byId = userRepository.findById(user_id);

        User user = null;
        if (byId.isPresent()) {
            user = byId.get();
            userRepository.deleteById(user_id);

            return "User with user_id " + user_id + " deleted successfully";
        } else
            return "User with user_id " + user_id + " not found";

    }

}

