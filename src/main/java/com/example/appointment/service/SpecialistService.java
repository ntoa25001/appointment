package com.example.appointment.service;

import com.example.appointment.model.Specialist;
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
public interface SpecialistService {

    Page<Specialist> findAll(Pageable pageable);

    Page<Specialist> findBySpecialistNameContaining(String specialistName, Pageable pageable);
}
