package org.studiumsystem.libtime.controller;

import org.apache.juli.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.studiumsystem.libtime.model.LibUser;
import org.studiumsystem.libtime.model.Task;
import org.studiumsystem.libtime.service.TaskService;
import org.studiumsystem.libtime.service.UserService;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class TaskController {

    private final UserService userService;
    private final TaskService taskService;
    private Logger logger = Logger.getLogger(TaskController.class.getName());

    public TaskController(UserService userService,
                          TaskService taskService){
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model){
        LibUser currentUser =userService.getUserSuccessAuth();
        List<Task> tasks = taskService.getAllTasksUnfinishedByUser(currentUser);
        logger.info("Num of unfinished tasks: " + tasks.size());
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@RequestParam String content,
                             Model model){
        LibUser currentUser =userService.getUserSuccessAuth();
        Task newTask = taskService.createTask(content, currentUser);
        List<Task> updateTasks = taskService.getTasks();
        updateTasks.add(newTask); // the tasks in the taskservice also changed?
        model.addAttribute("tasks", updateTasks);
        return "tasks";
    }

    @PutMapping("/tasks/finished_{id}")
    public String finishedTask(@PathVariable long id){
        logger.info("finished_id: " + id);
        taskService.finishTask(id);
        return "redirect:/tasks";
    }

}
