package com.example.atm.controller.request;

import lombok.Data;

@Data
public class TransactionWithRecipient {
    private Long recipientId;
    private Long quantity;
}
