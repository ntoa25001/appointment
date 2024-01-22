package com.example.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Table(name = "medicine")
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicine_name")
    private String name;

    @Column(name = "medicine_detail")
    private String detail;

    @ManyToOne
    @JsonIgnoreProperties(value = {"medicine", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "patientId")
    private Patient patient;
}
