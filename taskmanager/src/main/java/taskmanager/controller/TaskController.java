package taskmanager.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taskmanager.Task;
import taskmanager.User;
import taskmanager.repository.UserRepository;
import taskmanager.service.TaskService;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService service;

    @Autowired
    UserRepository userRepo;

    // CREATE TASK (linked to user)
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        User user = userRepo.findByUsername(username);
        return service.createTask(task, user);
    }

    // GET TASKS (role-based)
    @GetMapping("/tasks")
    public List<Task> getTasks(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String role = (String) request.getAttribute("role");

        if ("ADMIN".equals(role)) {
            return service.getAllTasks();
        }
        return service.getTasks(username);
    }

    // UPDATE TASK (edit title, status, deadline)
    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task updated, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String role = (String) request.getAttribute("role");
        return service.updateTask(id, updated, username, role);
    }

    // DELETE TASK (admin only)
    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable int id, HttpServletRequest request) {
        String role = (String) request.getAttribute("role");

        if (!"ADMIN".equals(role)) {
            return "Access Denied";
        }

        service.deleteTask(id);
        return "Deleted";
    }
}