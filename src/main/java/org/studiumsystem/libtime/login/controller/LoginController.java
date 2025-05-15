package org.studiumsystem.libtime.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.studiumsystem.libtime.login.service.UserService;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/libtime")
    public String getLogin(){
        return "login";
    }


    @GetMapping("/signup")
    public String signupGet(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ){
        //create user object in service
        userService.createUser(username, password);
        model.addAttribute("message", "You now have a new Account!");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "signup";
    }

    @PostMapping("/libtime")
    public String postLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model){
        if (username.equals("xia") && password.equals("2025")) {
            model.addAttribute("message", "Login Succeed");
            return "redirect:/main";
        }
        else {
            model.addAttribute("message", "Login failed");
            return "login";
        }
    }
}
