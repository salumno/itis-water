package ru.kpfu.itis.water.util;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.repositories.UserDataRepository;
import ru.kpfu.itis.water.security.details.UserDetailsImpl;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class AuthenticationUtil {
    private UserDataRepository userDataRepository;

    public AuthenticationUtil(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public UserData getUserDataByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserData userData = userDetails.getUserData();
        Long userId = userData.getId();
        Optional<UserData> userDataOptional = userDataRepository.findById(userId);
        if (userDataOptional.isPresent()) {
            return userDataOptional.get();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

}
