package org.studiumsystem.libtime.login.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @GetMapping("/main")
    public String getMain(){
        return "main";
    }


    @PostMapping("/main")
    public String checkOption(@RequestParam String option, RedirectAttributes redirectAttributes){
        if (option.equals("in")){
            redirectAttributes.addFlashAttribute(
                    "message", "Now have a nice learning time in the biblothek!");
        }else {
            redirectAttributes.addFlashAttribute("message", "See you soon!");
        }
        return "redirect:/main/stage";
    }

    @GetMapping("/main/stage")
    public String checkIn(Model model){
        return "check_stage";
    }
}
