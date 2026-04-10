package taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanager.Task;
import taskmanager.User;
import taskmanager.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository repo;

    public Task addTask(Task task){
        return repo.save(task);
    }
    public List<Task> getAllTasks(){
        return repo.findAll();
    }
    public void deleteTask(int id){
        repo.deleteById(id);
    }
    public List<Task> getTasks(String username) {
        return repo.findByUserUsername(username);
    }
    public Task createTask(Task task, User user) {
        task.setUser(user);
        return repo.save(task);
    }
}
