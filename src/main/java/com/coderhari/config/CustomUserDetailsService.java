package com.coderhari.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderhari.dao.UserRepository;
import com.coderhari.entitie.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUsetByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("user no found");

        } else {
            return new CustomUserDetailes(user);
        }
    }

}
