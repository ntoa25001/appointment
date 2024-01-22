package com.example.appointment.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private String name;

    private String email;

    private Long phone;

    private String idCard;

    private String address;

    private String birthYear;

    private String password;


}
