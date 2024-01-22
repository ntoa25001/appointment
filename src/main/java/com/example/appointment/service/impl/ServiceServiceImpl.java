package com.example.appointment.service.impl;

import com.example.appointment.Exceptions.ResourceNotFoundException;
import com.example.appointment.model.Service;
import com.example.appointment.repository.PatientRepository;
import com.example.appointment.repository.ServiceRepository;
import com.example.appointment.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Page<Service> findAll(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public Service getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "service id", serviceId));
    }

    @Override
    public Page<Service> findByServiceListNameContaining(String serviceName, Pageable pageable) {
        return serviceRepository.findByNameContaining(serviceName, pageable);
    }

    @Override
    public Optional<Service> findByDoctorId(Long serviceId) {
        return Optional.empty();
    }


}
