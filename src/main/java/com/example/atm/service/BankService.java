package com.example.atm.service;

import com.example.atm.entity.Client;
import com.example.atm.mapper.ClientMapper;
import com.example.atm.repository.BankRepository;
import com.example.atm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ClientMapper mapper = new ClientMapper();

    public ResponseEntity<String> addClient(Client client) {
        if (clientRepository.findClientById(client.getId()).isPresent()) {
            System.out.println("client already exist.");
            return new ResponseEntity<>("Client already exist.", HttpStatus.BAD_REQUEST);
        }
        client.setBank(bankRepository.findAll().get(0));
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        return new ResponseEntity<>(Long.toString(client.getId()), HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeClient(Long clientId) {
        Optional<Client> oldClient = clientRepository.findClientById(clientId);
        if (oldClient.isEmpty()) {
            return new ResponseEntity<>("Client doesn't exist.", HttpStatus.NOT_FOUND);
        }
        clientRepository.delete(oldClient.get());
        return new ResponseEntity<>(clientId.toString(), HttpStatus.OK);
    }

}
