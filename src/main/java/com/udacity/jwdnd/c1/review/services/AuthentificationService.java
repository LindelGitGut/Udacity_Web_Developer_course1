package com.udacity.jwdnd.c1.review.services;

import com.udacity.jwdnd.c1.review.mapper.UserMapper;
import com.udacity.jwdnd.c1.review.model.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.security.auth.login.CredentialNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;


@Service
public class AuthentificationService implements AuthenticationProvider {

   private final UserMapper userMapper;

    private final HashService hashService;




    public AuthentificationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("C ausgeführt");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println("Debugging Username" + username + " PAsswort" + password);

        User user = userMapper.getUser(username);

        if ( user != null){
            String saltString = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, saltString);
            if (user.getPassword().equals(hashedPassword)){
                System.out.println("User wurde authentifziert");
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
            else throw new AuthenticationCredentialsNotFoundException("Falsches Passwort");
        }
        else throw new AuthenticationCredentialsNotFoundException("User existiert nicht!");
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
