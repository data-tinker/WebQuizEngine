package com.ttymonkey.webquizengine.services.user;

import com.ttymonkey.webquizengine.models.domain.UserDetailsImpl;
import com.ttymonkey.webquizengine.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository repository;

    @Autowired
    public UserDetailsServiceImpl(
        UsersRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findUserEntityByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + username);
        }

        return new UserDetailsImpl(user);
    }
}
