package hu.molnaran.todobackend.service;

import hu.molnaran.todobackend.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findTodosByUserId(long id);
    List<Todo> findTodos();
    Todo findTodoById(long id);
    Todo createTodo(long userId, Todo todo);
    Todo removeTodo(long id);
    Todo updateTodo(long todoId, Todo todo);
}
