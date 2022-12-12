package com.example.atm.model.request;

import lombok.Data;

@Data
public class Transaction {
    private Long clientId;
    private Long quantity;
}
