package com.rankomat.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clients")
public class Client {

    public Client(Long id, String name,int phoneNumber, double amount) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    public Client(String name,int phoneNumber, double amount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "amount")
    private double amount;

    private Timestamp timestamp = null;

    private boolean updated = false;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", amount=" + amount +
                '}';
    }
}
