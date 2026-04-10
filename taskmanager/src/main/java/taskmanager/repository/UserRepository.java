package taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanager.User;

public interface UserRepository extends JpaRepository<User,Integer> {
User findByUsername(String username);
}
