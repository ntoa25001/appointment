package com.example.appointment.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ServiceService {

    Page<com.example.appointment.model.Service> findAll(Pageable pageable);

    com.example.appointment.model.Service getServiceById(Long serviceId);
    Page<com.example.appointment.model.Service> findByServiceListNameContaining(String serviceName, Pageable pageable);

    Optional<com.example.appointment.model.Service> findByDoctorId(Long serviceId);


}
