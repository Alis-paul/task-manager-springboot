package taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanager.Task;
import taskmanager.User;
import taskmanager.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository repo;

    public Task addTask(Task task) {
        return repo.save(task);
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public void deleteTask(int id) {
        repo.deleteById(id);
    }

    public List<Task> getTasks(String username) {
        return repo.findByUserUsername(username);
    }

    public Task createTask(Task task, User user) {
        task.setUser(user);
        return repo.save(task);
    }

    // UPDATE: finds existing task, updates fields, keeps same user & id
    public Task updateTask(int id, Task updated, String username, String role) {
        Optional<Task> existing = repo.findById(id);
        if (existing.isEmpty()) {
            throw new RuntimeException("Task not found");
        }

        Task task = existing.get();

        // Only admin or task owner can edit
        if (!"ADMIN".equals(role) && !task.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Access Denied");
        }

        task.setTitle(updated.getTitle());
        task.setStatus(updated.getStatus());
        task.setDeadline(updated.getDeadline());

        return repo.save(task);
    }
}