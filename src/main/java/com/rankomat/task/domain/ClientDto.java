package com.rankomat.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ClientDto {

    public ClientDto(Long id, String name,int phoneNumber, double amount) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    private Long id;
    private String name;
    private int phoneNumber;
    private double amount;
    private Timestamp timestamp = null;

    private boolean updated = false;



}
