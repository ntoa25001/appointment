package com.example.appointment.service;


import com.example.appointment.model.Time;
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
public interface TimeService {

    Page<Time> findAll(Pageable pageable);

    Page<Time> findByPeriodContaining(String period, Pageable pageable);
}
