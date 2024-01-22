package com.example.appointment.service;

import com.example.appointment.model.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MedicineService {


    Page<Medicine> findAll(Pageable pageable);

    Page<Medicine> findByMedicineNameContaining(String medicineName, Pageable pageable);

    Optional<Medicine> findByDoctorId(Long medicineId);
}
