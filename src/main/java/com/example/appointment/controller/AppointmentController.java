package com.example.appointment.controller;


import com.example.appointment.Exceptions.ResourceNotFoundException;
import com.example.appointment.dto.AppointmentDto;
import com.example.appointment.model.Appointment;
import com.example.appointment.model.Doctor;
import com.example.appointment.model.Patient;
import com.example.appointment.model.Service;
import com.example.appointment.repository.AppointmentRepository;
import com.example.appointment.service.AppointmentService;
import com.example.appointment.service.DoctorService;
import com.example.appointment.service.PatientService;
import com.example.appointment.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientService patientService;



    @PostMapping("/newAppointment")
    public ResponseEntity<?> newAppointment(@RequestBody AppointmentDto appointmentDto) {

        List<Service> services = new ArrayList<>();

        for(Service id : appointmentDto.getServices()) {
            Service service = serviceService.getServiceById(id.getId());
            service.setIsSelected(Boolean.TRUE);
            services.add(service);
        }

        Doctor doctor = doctorService.findByDoctorId(appointmentDto.getDoctorId());
        Patient patient = patientService.findByPatientId(appointmentDto.getPatientId());
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setPeriod(appointmentDto.getPeriod());
        appointment.setNote(appointmentDto.getNote());
        appointment.setDoctor(doctor);
        appointment.setServices(services);
        appointment.setStatus("pending");
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setPatient(patient);
        appointmentService.save(appointment);
        return ResponseEntity.ok("successfully");
    }
    @GetMapping("/appointment/{patientId}")
    public ResponseEntity<?> getAppointmentById(@PathVariable(name = "patientId") Long patientId) {
        List<Appointment> appointments = appointmentService.findByPatientId(patientId);
        if (appointments.isEmpty()) {
            return ResponseEntity.ok("Null");
        }
        return ResponseEntity.ok(appointments);
    }

    @DeleteMapping("/appointment/{patientId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable(name = "patientId") Long patientId) {
        Appointment appointments = appointmentRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patient id", patientId));
        appointments.setStatus("cancel");
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/appointments/{id}")
    public ResponseEntity<?> findAppointmentById(@PathVariable(name = "id") Long id) {
       Optional<Appointment> appointments = appointmentService.findById(id);
        if (appointments.isEmpty()) {
            return ResponseEntity.ok("null");
        }

        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/appointments/{id}")
    public ResponseEntity<?> updateAppointmentById(@RequestBody AppointmentDto appointmentDto, @PathVariable(name = "id") Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);

        appointment.setId(appointmentDto.getId());
        appointment.setPeriod(appointmentDto.getPeriod());
        appointment.setNote(appointmentDto.getNote());
        appointment.setStatus("pending");
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointmentService.save(appointment);
        return ResponseEntity.ok("successfully");
    }
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<?> deleteAppointmentById(@PathVariable(name = "id") Long id) {
        Appointment appointments = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patient id", id));
        appointments.setStatus("cancel");
        appointmentService.save(appointments);

        return ResponseEntity.ok(appointments);
    }

}
