package hu.molnaran.todobackend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class HelloWordController {

    @GetMapping(path = "/hello")
    public String getHelloWorld(){
        return "Hello World!";
    }
}
