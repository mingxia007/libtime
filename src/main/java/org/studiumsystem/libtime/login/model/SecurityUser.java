package org.studiumsystem.libtime.login.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


//User for security management
public class SecurityUser implements UserDetails {
    private final LibUser user;

    public SecurityUser(LibUser user){
        this.user = user;
    }

    //alle user with the same authorities to keep the project simple, so i set it to empty list
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
