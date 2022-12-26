package schedule.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDemoApplication.class, args);
    }

}
