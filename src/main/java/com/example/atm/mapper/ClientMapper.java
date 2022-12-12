package com.example.atm.mapper;

import com.example.atm.entity.Client;

public class ClientMapper {

    public void map(Client from, Client to) {
        from.setName(to.getName());
        from.setSurname(to.getSurname());
        from.setPassword(to.getPassword());
        from.setBalance(to.getBalance());
    }

}
