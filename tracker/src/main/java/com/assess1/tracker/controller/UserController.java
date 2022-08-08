package com.assess1.tracker.controller;

import com.assess1.tracker.entity.User;
import com.assess1.tracker.exception.ResourceNotFoundException;
import com.assess1.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
        String newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/viewAllUser")
    public List<User> viewAllUser(){

        return userService.viewAllUser();
    }

    @GetMapping("/viewUser")
    public ResponseEntity<User> viewUser(@RequestParam(value = "email_Id" , required = false) String email_Id,
                                         @RequestParam(value = "user_id" , required = false) Integer user_id)
                                        throws ResourceNotFoundException {
        User user = userService.viewUser(email_Id,user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    private String updateUser(@RequestBody User user) throws ResourceNotFoundException{
        return userService.updateUser(user , user.getUser_id());

    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam(name = "user_id") int user_id) {
        String delete = userService.deleteUser(user_id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}
