package org.studiumsystem.libtime.login.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.studiumsystem.libtime.login.service.UserService;

import java.util.logging.Logger;

@Controller
public class LoginController {

    private UserService userService;
    private Logger logger = Logger.getLogger(LoginController.class.getName());

    public LoginController(UserService userService,
                           PasswordEncoder passwordEncoder,
                           UserDetailsService userDetailsService){
        this.userService = userService;
    }

    @GetMapping("/libtime")
    public String getLogin(
            @RequestParam(required = false, value = "error") String error,
            Model model){
        if (error != null){
            model.addAttribute("error", "Incorrect username or password!");
        }
        return "login";
    }


    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }

    //create a new account
    //after create succeed click link return to log in
    @PostMapping("/register")
    public String registerPost(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ){
        //create user object in service
        if (userService.existUser(username)){
            model.addAttribute("failedMessage",
                    "The name is already been taken, choose another one");

        }else {
            userService.createUser(username, password);
            model.addAttribute("succeedMessage", "You now have an new Account!");
        }
        return "register";
    }

}
