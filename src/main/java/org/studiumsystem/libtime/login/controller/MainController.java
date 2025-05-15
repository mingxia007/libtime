package org.studiumsystem.libtime.login.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.studiumsystem.libtime.login.model.LibUser;
import org.studiumsystem.libtime.login.service.UserSessionManagementService;
import org.studiumsystem.libtime.login.service.UserService;

import java.util.logging.Logger;

@Controller
public class MainController {

    private UserService userService;
    private UserSessionManagementService session;
    private Logger logger = Logger.getLogger(MainController.class.getName());

    public MainController(UserService userService, UserSessionManagementService session){
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/main")
    public String getMain(){
        return "main";
    }


    @PostMapping("/main")
    public String checkOption(
            @RequestParam String option,
            RedirectAttributes redirectAttributes,
            Model model){
        String username = session.getUsername();
        LibUser libUser = userService.getUserByUsername(username);
        //can i in other way get user?
        if (option.equals("in")){
            redirectAttributes.addFlashAttribute(
                    "message", "Now have a nice learning time in the biblothek!");
            userService.checkIn(libUser);
        }else {
            redirectAttributes.addFlashAttribute("message", "See you soon!");
            String todayDuration = userService.chekOut(libUser);
            redirectAttributes.addFlashAttribute("duration", todayDuration);
        }
        return "redirect:/main/stage";
    }

    //goodbye message and show today's learning time
    @GetMapping("/main/stage")
    public String checkIn(Model model){
        return "check_stage";
    }
}
