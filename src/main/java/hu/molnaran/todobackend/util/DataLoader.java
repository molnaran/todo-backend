package hu.molnaran.todobackend.util;

import hu.molnaran.todobackend.model.Todo;
import hu.molnaran.todobackend.model.User;
import hu.molnaran.todobackend.repository.TodoRepository;
import hu.molnaran.todobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User bela= new User();
        bela.setName("Béla");
        bela.setEmail("bela@bela.hu");
        bela.setPassword("jelszóóó");

        Todo todo1= new Todo();
        todo1.setDescription("Első todo leírása");
        todo1.setDueDate(new Date());
        todo1.setTitle("Első todo");
        todo1.setUser(bela);

        Todo todo2= new Todo();
        todo2.setDescription("Második todo leírása");
        todo2.setDueDate(new Date());
        todo2.setTitle("Násodik todo");
        todo2.setUser(bela);
        bela.addTodo(todo1);
        bela.addTodo(todo2);

        userRepository.save(bela);

        User zita= new User();
        zita.setName("Zita");
        zita.setEmail("zita@zita.hu");
        zita.setPassword("zitajelszó");

        userRepository.save(zita);
    }
}
