package best.wecode.starting.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    /*@Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student issi = new Student("issi", "issi@matti.it", LocalDate.of(1998, APRIL, 29));
            Student don = new Student("don", "don@gmail.it", LocalDate.of(1993, DECEMBER, 14) );

            repository.saveAll(List.of(issi, don));
        };
    }*/
}
