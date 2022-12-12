package com.example.atm.repository;

import com.example.atm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaRepository<Client, Long> {

    Optional<Client> findClientById(Long id);

    Optional<Client> findClientByName(String name);
}
