package com.miu.lab2b;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private LocalDate dob;

    public Patient(int patientId, String firstName, String lastName, String phone, String email, String address, LocalDate dob) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
    }

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    // Getters/Setters
}
