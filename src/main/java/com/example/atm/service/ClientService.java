package com.example.atm.service;

import com.example.atm.config.security.CustomUser;
import com.example.atm.entity.Client;
import com.example.atm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // TODO add logger
    // TODO add pagination and sorting
    // TODO add unit tests
    public ResponseEntity<String> deposit(long quantity) {
        Client client = clientRepository.findClientById(getIdFromSecurityContext()).get();
        if (quantity <= 0) {
            return new ResponseEntity<>("Wrong amount of deposit.", HttpStatus.BAD_REQUEST);
        }
        client.setBalance(client.getBalance() + quantity);
        clientRepository.save(client);
        return new ResponseEntity<>(Long.toString(client.getBalance()), HttpStatus.OK);
    }

    public ResponseEntity<String> retrieve(long quantity) {
        Client client = clientRepository.findClientById(getIdFromSecurityContext()).get();
        if (quantity <= 0) {
            return new ResponseEntity<>("Wrong amount of deposit.", HttpStatus.BAD_REQUEST);
        }
        if (client.getBalance() < quantity) {
            return new ResponseEntity<>("Insufficient balance.", HttpStatus.BAD_REQUEST);
        }
        client.setBalance(client.getBalance() - quantity);
        clientRepository.save(client);
        return new ResponseEntity<>(Long.toString(client.getBalance()), HttpStatus.OK);
    }

    public ResponseEntity<String> transfer(Long recipientId, long quantity) {
        Client client = clientRepository.findClientById(getIdFromSecurityContext()).get();
        Optional<Client> optionalRecipient = clientRepository.findById(recipientId);
        if (optionalRecipient.isEmpty()) {
            return new ResponseEntity<>("Unknown recipient.", HttpStatus.BAD_REQUEST);
        }
        Client recipient = optionalRecipient.get();
        if (quantity <= 0) {
            return new ResponseEntity<>("Wrong amount of deposit.", HttpStatus.BAD_REQUEST);
        }
        if (client.getBalance() < quantity) {
            return new ResponseEntity<>("Insufficient balance.", HttpStatus.BAD_REQUEST);
        }
        client.setBalance(client.getBalance() - quantity);
        recipient.setBalance(recipient.getBalance() + quantity);
        clientRepository.save(client);
        clientRepository.save(recipient);
        return new ResponseEntity<>(client.getBalance() + " " + recipient.getBalance(), HttpStatus.OK);
    }

    public ResponseEntity<Client> get(long id) {
        if (clientRepository.findClientById(id).isPresent()) {
            return new ResponseEntity<>(clientRepository.findClientById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private Long getIdFromSecurityContext() {
        return ((CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
