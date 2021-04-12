package best.wecode.starting;

import best.wecode.starting.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class StartingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartingApplication.class, args);
    }
}
