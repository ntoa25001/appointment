package com.example.appointment.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "appointment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_date", nullable = false)
    private String appointmentDate;

    @Column(name = "period", nullable = false, length = 225)
    private String period;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @ManyToOne
    @JsonIgnoreProperties(value = {"Appointment", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JsonIgnoreProperties(value = {"Appointment", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    private Collection<Service>  services;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "appointments_services",
//            joinColumns = @JoinColumn(name = "appointment_id"),
//            inverseJoinColumns = @JoinColumn(name = "service_id")
//    )
//    private Collection<Service> services;

    public Appointment() {
    }

    public Appointment(Long id, String appointmentDate, String period, String note, String status, Patient patient, Doctor doctor, Collection<Service> services) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.period = period;
        this.note = note;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
        this.services = services;
    }

    public Collection<Service> getServices() {
        return services;
    }

    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPatient() {
        if(patient == null) {
            return "null";
        }
        return patient.getName();
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getDoctor() {
        if(doctor == null) {
            return null;
        }
        return doctor.getDoctorId();
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
