package hu.molnaran.todobackend.controller;


import hu.molnaran.todobackend.dto.CreateTodoDto;
import hu.molnaran.todobackend.dto.PatchTodoDto;
import hu.molnaran.todobackend.dto.TodoDto;
import hu.molnaran.todobackend.model.Todo;
import hu.molnaran.todobackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping(value = {"/user/{userId}/todo/"})
    public List<TodoDto> getTodosForUser(@PathVariable(value = "userId") long userId){
        List<Todo> todoList=todoService.findTodosByUserId(userId);
        return TodoMapper.mapTodosToTodoDtos(todoList);
    }

    @GetMapping(value = {"/todo/"})
    public List<TodoDto> getTodos(){
        List<Todo> todoList=todoService.findTodos();
        return TodoMapper.mapTodosToTodoDtos(todoList);
    }

    @PostMapping(value = {"/user/{userId}/todo/"})
    public TodoDto createTodoForUser(@PathVariable(value = "userId") long userId, @RequestBody @Valid CreateTodoDto createTodoDto){
        Todo todo=todoService.createTodo(userId, TodoMapper.mapCreateUserDtoToUser(createTodoDto));
        return TodoMapper.mapTodoToTodoDto(todo);
    }

    @PatchMapping(value = {"/todo/{todoId}"})
    public TodoDto patchTodoForUser(@PathVariable(value = "todoId") long todoId, @RequestBody @Valid  PatchTodoDto patchTodoDto){
        Todo todo=todoService.updateTodo(todoId, TodoMapper.mapPatchUserDtoToUser(patchTodoDto));
        return TodoMapper.mapTodoToTodoDto(todo);
    }

    @DeleteMapping(value = {"/todo/{todoId}"})
    public TodoDto deleteTodo(@PathVariable(value = "todoId") long todoId){
        Todo removedTodo= todoService.removeTodo(todoId);
        return TodoMapper.mapTodoToTodoDto(removedTodo);
    }
}
