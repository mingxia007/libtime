package org.studiumsystem.libtime.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/libtime")
    public String getLogin(){
        return "login";
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
