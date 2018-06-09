package ru.kpfu.itis.water.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.water.form.RegistrationForm;
import ru.kpfu.itis.water.model.User;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.repositories.UserDataRepository;
import ru.kpfu.itis.water.security.roles.UserRole;
import ru.kpfu.itis.water.security.status.UserStatus;
import ru.kpfu.itis.water.services.UserService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class UserServiceImpl implements UserService {

    private UserDataRepository userDataRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDataRepository userDataRepository, PasswordEncoder passwordEncoder) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(RegistrationForm form) {
        UserRole role = UserRole.USER;
        User user = User.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .role(role)
                .build();
        UserData userData = UserData.builder()
                .login(form.getLogin())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .user(user)
                .userRole(role)
                .status(UserStatus.CONFIRMED)
                .build();
        userDataRepository.save(userData);
    }
}
