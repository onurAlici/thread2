package com.forum.thread.services;

import com.forum.thread.entity.UserObject;
import com.forum.thread.models.UserpDTO;
import com.forum.thread.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserpDTO> o = userRepository.findUserDTOByUsername(username);
        if (o.isPresent()) {
            UserpDTO user = o.get();
            return toUserDetails(user);
        }
        else {
            throw new UsernameNotFoundException(username);
        }

    }

    private UserDetails toUserDetails(UserpDTO userObject) {
        return User.withUsername(userObject.getUsername())
                .password(userObject.getPassword())
                .roles("USER")
                .build();
    }

}
