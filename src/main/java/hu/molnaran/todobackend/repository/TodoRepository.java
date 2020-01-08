package hu.molnaran.todobackend.repository;

import hu.molnaran.todobackend.model.Todo;
import hu.molnaran.todobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}
