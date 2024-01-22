package com.example.appointment.dto;


import com.example.appointment.model.Specialist;
import com.example.appointment.model.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private Long doctorId;

    private String name;

    private String avatar;

    private String email;

    private String phone;

    private String speciality;

    private Specialist specialist;

    private Collection<Time> times;


}
