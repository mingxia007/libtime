package org.studiumsystem.libtime.login.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.studiumsystem.libtime.login.service.UserSessionManagementService;
import org.studiumsystem.libtime.login.service.UserService;

import java.util.logging.Logger;

@Controller
public class LoginController {

    private UserService userService;
    private UserSessionManagementService session;
    private Logger logger = Logger.getLogger(LoginController.class.getName());

    public LoginController(UserService userService,
                           UserSessionManagementService session,
                           PasswordEncoder passwordEncoder,
                           UserDetailsService userDetailsService){
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/libtime")
    public String getLogin(){
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
        userService.createUser(username, password);
        model.addAttribute("message", "You now have an new Account!");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "register";
    }

    //log in
    @PostMapping("/libtime")
    public String postLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model){
        boolean authenticated =  userService.authenUser(username, password);
        //by formLogin in other page redirect
        logger.info("Authentication state: " + authenticated);
        if (authenticated) {
            model.addAttribute("message", "Login Succeed");
            session.setUsername(username);
            return "redirect:/main";
        }
        else {
            model.addAttribute("message", "Login failed");
            return "login";
        }
    }
}
