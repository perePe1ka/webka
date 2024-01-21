package ru.web.laba_web2.services.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.web.laba_web2.repositories.IUserRepository;

import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
    private IUserRepository userRepository;

    public AppUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(u -> new User(
                        u.getUsername(),
                        u.getPassword(),
                        u.getRole().stream()
                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                                .collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
