package com.udacity.jwdnd.c1.review.services;


import com.udacity.jwdnd.c1.review.mapper.UserMapper;
import com.udacity.jwdnd.c1.review.model.User;

import java.util.Base64;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.security.SecureRandom;

@Service
public class UserService {

    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public Boolean isUserAvailable(String username){
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user){
        SecureRandom random = new SecureRandom();
        byte [] salt = new byte[16];
        random.nextBytes(salt);
        String saltString = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), saltString);

       return userMapper.createUser(new User(null, user.getUsername(),saltString,hashedPassword,user.getFirstname(), user.getLastname()));
    }

    public User getUser(String username){
        return userMapper.getUser(username);
    }

    public String getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
