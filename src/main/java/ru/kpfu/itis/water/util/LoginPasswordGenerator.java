package ru.kpfu.itis.water.util;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.water.model.LoginPassword;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class LoginPasswordGenerator {

    public LoginPassword generate() {
        return LoginPassword.builder().login(generateLogin()).password(generatePassword()).build();
    }

    private String generatePassword() {
        Generator generator = new Generator.GeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .usePunctuation(true)
                .build();
        return generator.generate(10);
    }

    private String generateLogin() {
        Generator generator = new Generator.GeneratorBuilder()
                .useDigits(true)
                .build();
        return generator.generate(10);
    }
}
