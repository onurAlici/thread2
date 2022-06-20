package com.forum.thread.controllers;


import com.forum.thread.entity.Thread;
import com.forum.thread.entity.UserObject;
import com.forum.thread.models.UserDTO;
import com.forum.thread.models.UserModel;
import com.forum.thread.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/user")
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(com.forum.thread.controllers.UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public UserDTO login(Principal principal ) {
        UserDTO p = userService.getUserWithUsername(principal.getName());

        System.out.println(p);
        System.out.println("login happened");

        return p;
    }
    @PostMapping("/login")
    public Principal loginPOST(Principal principal) {
        Principal p = principal;

        return p;
    }

    @GetMapping("/{username}")
    public UserDTO getUserWithUsername(@PathVariable("username") String username) {

        UserDTO u = userService.getUserWithUsername(username);

        return u;

    }

    @GetMapping("/{id}")
    public UserObject getUserWithUsername(@PathVariable("id") Long id) {

        UserObject u = userService.getUserWithId(id);

        return u;

    }

    @PostMapping("/register")
    public UserObject createUser(@RequestBody UserModel newUser ) {
        UserObject u = userService.createUser(newUser);
        userService.addUser(u);
        log.info("new user have been created...");
        log.info(String.valueOf(u.getUsername()));
        log.info(String.valueOf(u.getId()));
        return u;
    }

}
