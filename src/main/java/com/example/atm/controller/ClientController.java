package com.example.atm.controller;

import com.example.atm.controller.request.Transaction;
import com.example.atm.controller.request.TransactionWithRecipient;
import com.example.atm.entity.Client;
import com.example.atm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    ClientService service;

    @PutMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody Transaction transaction) {
        return service.deposit(transaction.getQuantity());
    }

    @PutMapping("/retrieve")
    public ResponseEntity<String> retrieve(@RequestBody Transaction transaction) {
        return service.retrieve(transaction.getQuantity());
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransactionWithRecipient transaction) {
        return service.transfer(transaction.getRecipientId(), transaction.getQuantity());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Client> get(@PathVariable String id) {
        return service.get(Long.parseLong(id));
    }

}
