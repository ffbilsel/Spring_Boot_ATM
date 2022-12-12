package com.example.atm.config.security;

import com.example.atm.entity.Client;
import com.example.atm.repository.BankRepository;
import com.example.atm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomUserDetails implements UserDetailsService {

    @Autowired
    BankRepository bankRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (username.equals("admin")) {
            authorityList.add(new SimpleGrantedAuthority("ADMIN"));
            return new User(username, bankRepository.findAll().get(0).getPassword(), authorityList);
        }
        Optional<Client> optional = clientRepository.findClientByName(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Username could not be found.");
        }
        Client client = optional.get();
        authorityList.add(new SimpleGrantedAuthority("USER"));
        return new CustomUser(username, client.getPassword(), client.getId(), authorityList);
    }
}
