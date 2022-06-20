package com.forum.thread.services;


import com.forum.thread.entity.UserObject;
import com.forum.thread.models.UserDTO;
import com.forum.thread.models.UserModel;
import com.forum.thread.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Lazy
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;


    public boolean checkUserPassword(String username1, String password1) {
        Optional<UserObject> o =
                userRepository.findUserObjectByUsername(username1);

        if(o.isPresent()) {
            UserObject u = o.get();
            if (encoder.matches(password1, u.getPassword())) {
                return true;
            } else {
                throw new BadCredentialsException("Bad credentials.");
            }
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }

    }
    public UserObject createUser(UserModel user) {
        String Username = user.getUsername();
        String name = user.getName();
        String password = user.getPassword();
        UserObject u = new UserObject(Username, name, password);
        return u;
    }
    public void addUser(UserObject user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public UserDTO getUserWithUsername(String username) {
        Optional<UserDTO> o =
                userRepository.findDTOByUsername(username);
        UserDTO u = o.get();
        return u;
    }
    public UserObject getUserWithId(Long id) {
        Optional<UserObject> o =
                userRepository.findById(id);
        UserObject u = o.get();
        return u;
    }
}
