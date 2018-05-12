package ru.kpfu.itis.water;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */


@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.kpfu.itis.water.repositories")
@EntityScan(basePackages = "ru.kpfu.itis.water.model")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
