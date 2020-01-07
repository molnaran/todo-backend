package hu.molnaran.todobackend.util;

import hu.molnaran.todobackend.model.User;
import hu.molnaran.todobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {


    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User bela= new User();
        bela.setName("Béla");
        bela.setEmail("bela@bela.hu");
        bela.setPassword("jelszóóó");

        userRepository.save(bela);

        User zita= new User();
        zita.setName("Zita");
        zita.setEmail("zita@zita.hu");
        zita.setPassword("zitajelszó");

        userRepository.save(zita);

    }
}
