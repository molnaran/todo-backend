package hu.molnaran.todobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
public class DumpController {

    private final Object monitor = new Object();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DumpController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/dumpDb")
    @ResponseBody
    public void dumpDb() throws IOException {
        synchronized (this.monitor) {
            File dump = new File("dump.sql");
            if (dump.exists()) {
                dump.delete();
            }
            this.jdbcTemplate.execute("SCRIPT TO 'dump.sql'");
        }
    }
}
