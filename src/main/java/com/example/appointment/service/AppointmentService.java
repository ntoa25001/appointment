package com.example.appointment.service;


import com.example.appointment.model.Appointment;
import com.example.appointment.model.Doctor;
import com.example.appointment.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentService {
    List<Appointment> findByPatient(Patient patient);

    List<Appointment> findByDoctor(Doctor doctor);

    public Appointment getAppointmentById(Long appointmentId);

    <S extends Appointment> S save(S entity);

    Optional<Appointment> findById(Long aLong);

    List<Appointment> findByPatientId(Long patientId);
}
