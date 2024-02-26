package com.udacity.jwdnd.c1.review.controller;


import com.udacity.jwdnd.c1.review.model.User;
import com.udacity.jwdnd.c1.review.services.AuthentificationService;
import com.udacity.jwdnd.c1.review.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    public LoginController() {
    }

    @GetMapping
    String getLoginPage(Model model) {
        return "login";
    }
}
