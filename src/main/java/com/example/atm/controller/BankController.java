package com.example.atm.controller;

import com.example.atm.entity.Client;
import com.example.atm.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {

    @Autowired
    BankService bankService;

    @PostMapping("/add")
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        return bankService.addClient(client);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeClient(@PathVariable String id) {
        return bankService.removeClient(Long.parseLong(id));
    }
}
