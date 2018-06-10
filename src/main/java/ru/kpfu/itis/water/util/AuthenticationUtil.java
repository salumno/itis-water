package ru.kpfu.itis.water.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.water.form.client.AuthenticationResponse;
import ru.kpfu.itis.water.form.client.LoginPasswordForm;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.repositories.UserDataRepository;
import ru.kpfu.itis.water.security.details.UserDetailsImpl;
import ru.kpfu.itis.water.security.roles.UserRole;
import ru.kpfu.itis.water.security.status.UserStatus;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class AuthenticationUtil {
    private UserDataRepository userDataRepository;
    private PasswordEncoder passwordEncoder;

    public AuthenticationUtil(UserDataRepository userDataRepository, PasswordEncoder passwordEncoder) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserData getUserDataByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserData userData = userDetails.getUserData();
        Long userId = userData.getId();
        Optional<UserData> userDataOptional = userDataRepository.findOneById(userId);
        if (userDataOptional.isPresent()) {
            return userDataOptional.get();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public String defineDefaultURL(Authentication authentication) {
        UserData userData = getUserDataByAuthentication(authentication);
        if (userData.getUserRole().equals(UserRole.USER)) {
            return "/user/profile";
        } else {
            return "/admin";
        }
    }

    public boolean isCredentialsValid(LoginPasswordForm form) {
        Optional<UserData> userDataOptional = userDataRepository.findOneByLogin(form.getLogin());
        if (userDataOptional.isPresent()) {
            UserData userData = userDataOptional.get();
            if (passwordEncoder.matches(form.getPassword(), userData.getHashPassword())) {
                return userData.getStatus().equals(UserStatus.CONFIRMED);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public AuthenticationResponse createAuthResponse(LoginPasswordForm form) {
        UserData userData = userDataRepository.findOneByLogin(form.getLogin()).orElseThrow(
                () -> new IllegalArgumentException("User with login: " + form.getLogin() + " not found")
        );
        return new AuthenticationResponse(userData.getId(), true);
    }
}
