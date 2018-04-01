package ru.kpfu.itis.water.security.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.repositories.UserDataRepository;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDataRepository userDataRepository;

    public UserDetailsServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserData userData = userDataRepository.findOneByLogin(login).orElseThrow(
                () -> new IllegalArgumentException("User " + login + " not found!")
        );
        return new UserDetailsImpl(userData);
    }
}
