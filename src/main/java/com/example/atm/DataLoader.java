package com.example.atm;

import com.example.atm.entity.Bank;
import com.example.atm.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void run(ApplicationArguments args) {
        bankRepository.save(new Bank(0L,"admin", passwordEncoder.encode("1234"), new ArrayList<>()));
    }
}