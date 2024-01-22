package com.example.appointment.controller;


import com.example.appointment.model.Doctor;
import com.example.appointment.repository.DoctorRepository;
import com.example.appointment.service.DoctorService;
import com.example.appointment.util.ImageUploads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Multipart;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ImageUploads imageUploads;


    //Get all doctor
    @GetMapping("/doctors")
    public  ResponseEntity<?> getAllDoctor(@RequestParam(value = "name",required = false) String name
            ,@RequestParam(value = "page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(30);

        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("name"));
        Page<Doctor> resultPage = null;
        if (StringUtils.hasText(name)) {
            resultPage = doctorService.findByNameContaining(name, pageable);

        } else {
            resultPage = doctorService.findAll(pageable);
        }
        return ResponseEntity.ok(resultPage);
    }
    //Get doctor by id
    @GetMapping("/doctors/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable(name = "id") Long id) {
        Optional<Doctor> doctor = doctorService.findById(id);
        if (doctor.isEmpty()) {
            return ResponseEntity.ok("Null");
        }
        return ResponseEntity.ok(doctor);
    }

    @PostMapping("doctors/{id}")
    public ResponseEntity<?> updateDoctorById(@PathVariable(name = "id") Long id, @RequestParam("avatar") MultipartFile file) throws IOException {
        Doctor doctor = doctorRepository.findByDoctorId(id);
        if(file == null){
            doctor.setAvatar(null);
        }else{
            if(imageUploads.uploadImage(file)){
                System.out.println("Upload successfully");
            }
            doctor.setAvatar(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        doctorService.save(doctor, file);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/doctors/times")
    public ResponseEntity<?> findDoctorsByPeriodAndSpecialist(
            @RequestParam(name = "period") String period,
            @RequestParam(name = "specialistId") Long specialistId) {
        List<Doctor> doctors = doctorService.getDoctorTime(period, specialistId);
        if(doctors.isEmpty()) {
            return ResponseEntity.ok("Null");
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }


}
