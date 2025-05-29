package org.studiumsystem.libtime.service;

import org.springframework.stereotype.Service;
import org.studiumsystem.libtime.model.LibUser;
import org.studiumsystem.libtime.model.Task;
import org.studiumsystem.libtime.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private  List<Task> tasks;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task createTask(String content, LibUser user){
        Task task = new Task();
        task.setUserId(user.getId());
        task.setContent(content);
        taskRepository.save(task);
        return task;
    }

    public List<Task> getAllTasksUnfinishedByUser(LibUser user){
        long userId = user.getId();
        tasks =  taskRepository.findAllByUserId(userId);
        return tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void finishTask(long id){
        taskRepository.updateTaskFinishedById(id);
    }

    public void updateTask(long id, String contentEdit){
        taskRepository.updateTaskContentById(id, contentEdit);
    }

    public void deleteTask(long id){
        taskRepository.deleteTaskById(id);
    }

}
