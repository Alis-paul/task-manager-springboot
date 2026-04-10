package taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanager.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByUserUsername(String username);
}
