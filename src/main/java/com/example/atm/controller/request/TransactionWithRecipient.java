package com.example.atm.model.request;

import lombok.Data;

@Data
public class TransactionWithRecipient {
    private Long clientId;
    private Long recipientId;
    private Long quantity;
}
