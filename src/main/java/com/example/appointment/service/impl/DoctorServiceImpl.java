package com.example.appointment.service.impl;

import com.example.appointment.model.Doctor;
import com.example.appointment.repository.DoctorRepository;
import com.example.appointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Multipart;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public Optional<Doctor> findById(Long aLong) {
        return doctorRepository.findById(aLong);
    }

    @Override
    public Doctor save(Doctor doctor, MultipartFile file) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Page<Doctor> findAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Page<Doctor> findByNameContaining(String name, Pageable pageable) {
        return doctorRepository.findByNameContaining(name,pageable);
    }

    @Override
    public Doctor findByDoctorId(Long id) {
        return doctorRepository.findByDoctorId(id);
    }

    @Override
    public List<Doctor> getDoctorTime(String period, Long specialistId) {
        return doctorRepository.getDoctorTime(period,specialistId);
    }
}
