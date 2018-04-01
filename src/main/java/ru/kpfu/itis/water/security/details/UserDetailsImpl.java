package ru.kpfu.itis.water.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.security.status.UserStatus;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public class UserDetailsImpl implements UserDetails {
    private UserData userData;

    public UserDetailsImpl(UserData userData) {
        this.userData = userData;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(userData.getUserRole().toString());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return userData.getHashPassword();
    }

    @Override
    public String getUsername() {
        return userData.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userData.getStatus().equals(UserStatus.CONFIRMED);
    }

    public UserData getUserData() {
        return userData;
    }
}
