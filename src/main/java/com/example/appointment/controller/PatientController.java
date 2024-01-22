package com.example.appointment.controller;


import com.example.appointment.dto.PatientDto;
import com.example.appointment.model.Patient;
import com.example.appointment.service.MedicineService;
import com.example.appointment.service.PatientService;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")

public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private MedicineService medicineService;

    //Register patient
    @PostMapping("/newPatient")
    public ResponseEntity<?> newPatient(@RequestBody PatientDto patientDto) throws ParseException {

        if (patientService.existsPatientByPhone(patientDto.getPhone())) {
            return ResponseEntity.ok("Phone number already exists");
        }
        if (patientService.existsPatientByIdCard(patientDto.getIdCard())) {
            return ResponseEntity.ok("ID card number already exists");
        }
        if(!isValidEmail(patientDto.getEmail())) {
            return ResponseEntity.ok("Invalid email address");
        }
        if(StringUtils.isBlank(patientDto.getName())) {
            return ResponseEntity.ok("Name is required");
        }
        if(StringUtils.isBlank(String.valueOf(patientDto.getPhone()))) {
            return ResponseEntity.ok("Phone is required");
        }
        if(StringUtils.isBlank(patientDto.getIdCard())) {
            return ResponseEntity.ok("IdCard is required");
        }
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDto, patient);
        String birthYearStr = patientDto.getBirthYear();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthYearDate = formatter.parse(birthYearStr);
        patient.setBirthYear(birthYearDate);
        patientService.save(patient);
        return ResponseEntity.ok("successfully");
    }


    private boolean isValidEmail(String email) {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    //Get patient by Id
    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable(name = "id") Long id) {
        Optional<Patient> patient = patientService.findById(id);
        if (patient.isEmpty()) {
            return ResponseEntity.ok("Null");
        }
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        Patient patient = patientService.findByEmailAndPassword(email, password);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    @GetMapping("/patients")
    public ResponseEntity<?> getDoctorByEmail(@RequestParam(name = "email") String email) {
        Patient patient = patientService.findByEmail(email);
        if (patient == null) {
            return ResponseEntity.ok("Null");
        }
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> updatePatientByEmail(@PathVariable("email") String email, @RequestBody PatientDto patient) {
             Patient existingPatient = patientService.findByEmail(email);
            existingPatient.setName(patient.getName());
            existingPatient.setAddress(patient.getAddress());
            existingPatient.setIdCard(patient.getIdCard());
            existingPatient.setPhone(patient.getPhone());
            // Lưu thông tin bệnh nhân đã cập nhật vào cơ sở dữ liệu
            patientService.save(existingPatient);
            return ResponseEntity.ok(existingPatient);

    }

//    @PostMapping("/patients")
//    public ResponseEntity<?> updateByEmail(@RequestParam(name = "email") String email, @RequestBody PatientDto patientDto) {
//        Patient patient = patientService.findByEmail(email);
//        if (patient == null) {
//            return ResponseEntity.ok("Null");
//        }
//        Patient patient1 = patientService.save1(patient);
//        return ResponseEntity.ok(patient1);
//    }
    @GetMapping("/patient/{patientId}/medicine/list")
    public ResponseEntity<?> getAllServiceByPatient(@PathVariable(name = "patientId") Long patientId){
        return ResponseEntity.ok().body(patientService.getAllMedicinePatient(patientId));
    }
}
