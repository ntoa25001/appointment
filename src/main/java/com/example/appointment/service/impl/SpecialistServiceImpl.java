package com.example.appointment.service.impl;


import com.example.appointment.model.Specialist;
import com.example.appointment.repository.SpecialistRepository;
import com.example.appointment.service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class SpecialistServiceImpl implements SpecialistService {
    @Autowired
    private SpecialistRepository specialistRepository;


    @Override
    public Page<Specialist> findAll(Pageable pageable) {
        return specialistRepository.findAll(pageable);
    }

    @Override
    public Page<Specialist> findBySpecialistNameContaining(String specialistName, Pageable pageable) {
        return specialistRepository.findBySpecialistNameContaining(specialistName, pageable);
    }
}
