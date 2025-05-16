package org.studiumsystem.libtime.login.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.studiumsystem.libtime.login.common.NotCheckInException;
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
    public String getMain(Model model){
        return "main";
    }


    //check in
    @PostMapping("/main/checkin")
    public String checkIn(RedirectAttributes redirectAttributes){
        String username = session.getUsername();
        LibUser libUser = userService.getUserByUsername(username);
        redirectAttributes.addFlashAttribute(
                "message", "Now have a nice learning time in the biblothek!");
        //why i dont show the message?
        userService.checkIn(libUser);
        return "redirect:/main/stage";
    }


    //check out
    //must not chen out when not check in
    //show stayed time after checked out
    @PostMapping("/main/checkout")
    public String checkOut(RedirectAttributes redirectAttributes){
        String username = session.getUsername();
        LibUser libUser = userService.getUserByUsername(username);
        String todayDuration;
        try{
            todayDuration = userService.chekOut(libUser);
        }
        catch (NotCheckInException e){
            redirectAttributes.addFlashAttribute("message", "You have not check in today");
            return "redirect:/main"; //i have trouble hier
        }
        redirectAttributes.addFlashAttribute("message", "See you soon!");
        redirectAttributes.addFlashAttribute("duration", todayDuration);
        return "redirect:/main/stage";
    }


    //goodbye message and show today's learning time
    @GetMapping("/main/stage")
    public String afterCheck(Model model){
        return "check_stage";
    }
}
