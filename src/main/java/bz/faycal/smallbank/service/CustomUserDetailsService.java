package bz.faycal.smallbank.service;

import bz.faycal.smallbank.entities.User;
import bz.faycal.smallbank.repository.UserRepository;

import bz.faycal.smallbank.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(s).orElseThrow(()->
                new UsernameNotFoundException("User not found with username : " + s));

        return UserPrincipal.create(user);
    }
}
