package ru.kpfu.itis.water.security.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.repositories.UserDataRepository;
import ru.kpfu.itis.water.security.status.UserStatus;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class AuthProvider implements AuthenticationProvider {

    private UserDataRepository userDataRepository;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public AuthProvider(UserDataRepository userDataRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login  = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<UserData> userDataOptional = userDataRepository.findOneByLogin(login);
        if (userDataOptional.isPresent()) {
            UserData userData = userDataOptional.get();

            if (passwordEncoder.matches(password, userData.getHashPassword())) {

                if (!userData.getStatus().equals(UserStatus.CONFIRMED)) {
                    throw new BadCredentialsException("Confirmation is failed");
                }

            } else {
                throw new BadCredentialsException("Invalid login or password");
            }

        } else {
            throw new BadCredentialsException("Invalid login or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        Collection<? extends GrantedAuthority> grantedAuthority = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails, password, grantedAuthority);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
