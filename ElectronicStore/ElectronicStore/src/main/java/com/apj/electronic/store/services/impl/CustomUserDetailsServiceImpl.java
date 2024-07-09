package com.apj.electronic.store.services.impl;

import com.apj.electronic.store.entities.User;
import com.apj.electronic.store.exceptions.ResourceNotFoundException;
import com.apj.electronic.store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("user with given name is not found !!"));
        return user;
    }
}
