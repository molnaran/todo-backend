package hu.molnaran.todobackend.service;

import hu.molnaran.todobackend.model.Todo;
import hu.molnaran.todobackend.model.User;
import hu.molnaran.todobackend.repository.TodoRepository;
import hu.molnaran.todobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Todo> findTodosByUserId(long id) {
        User user=userRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("User not found!"));
        List<Todo> todoList=todoRepository.findByUser(user);
        return todoList;
    }

    @Override
    public List<Todo> findTodos() {
        List<Todo> todoList=todoRepository.findAll();
        return todoList;
    }

    @Override
    public Todo findTodoById(long id) {
        Todo todo= todoRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Todo not found!"));
        return todo;
    }

    @Override
    public Todo createTodo(long userId, Todo todo) {
        User user=userRepository.findById(userId).orElseThrow(() ->  new EntityNotFoundException("User not found!"));
        user.addTodo(todo);
        todo.setUser(user);
        //userRepository.save(user);
        return todo;
    }

    @Override
    public Todo removeTodo(long id) {
        Todo todoToBeRemoved= todoRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Todo not found!"));
        todoRepository.deleteById(id);
        return todoToBeRemoved;
    }

    @Override
    public Todo updateTodo(long id, Todo todo) {
        Todo todoToBeUpdated= todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo not found!"));
        if (todo.getDescription()!=null){
            todoToBeUpdated.setDescription(todo.getDescription());
        }
        if (todo.getTitle()!=null){
            todoToBeUpdated.setTitle(todo.getTitle());
        }
        if (todo.getDueDate()!=null){
            todoToBeUpdated.setDueDate(todo.getDueDate());
        }
        if (todo.isDone()!=null){
            todoToBeUpdated.setDone(todo.isDone());
        }
        //todoRepository.save(todoToBeUpdated);
        return todoToBeUpdated;
    }
}
