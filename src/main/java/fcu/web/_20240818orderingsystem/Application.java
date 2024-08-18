package fcu.web._20240818orderingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "fcu.web._20240818orderingsystem")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}