package com.udacity.jwdnd.c1.review.controller;


import com.udacity.jwdnd.c1.review.model.User;
import com.udacity.jwdnd.c1.review.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String getSignUp(Model model, User user) {

        return "signup";
    }

    @PostMapping
    String createUser(Model model, User user) {

        String errormsg = null;

        if (userService.isUserAvailable(user.getUsername())) {
            System.out.println("Username ist noch nicht vergeben");


            if (userService.createUser(user) > 0) {

                System.out.println("User " + user.getUsername() + " wurde erfolgreich erstellt");
            } else {
                errormsg = "Es ist ein Fehler beim erstellen des Users aufgetreten, bitte versuchen Sie es nochmal";
                model.addAttribute("errormsg", errormsg);
            }

        } else {
            errormsg = "Username ist bereits vergeben!";

            model.addAttribute("errormsg", errormsg);
        }

        System.out.println("" + user.getUsername() + " " + user.getPassword() + " " + user.getFirstname() + " " + user.getLastname());

        if (errormsg == null){
            model.addAttribute("signupsuccess", true);
            System.out.println("Erstellen des Users erfolgreich!");
        }

        return "signup";
    }

}
