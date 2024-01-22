package com.example.appointment.service.impl;

import com.example.appointment.model.Medicine;
import com.example.appointment.repository.MedicineRepository;
import com.example.appointment.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Page<Medicine> findAll(Pageable pageable) {
        return medicineRepository.findAll(pageable);
    }

    @Override
    public Page<Medicine> findByMedicineNameContaining(String medicineName, Pageable pageable) {
        return medicineRepository.findByNameContaining(medicineName, pageable);
    }

    @Override
    public Optional<Medicine> findByDoctorId(Long medicineId) {
        return Optional.empty();
    }
}
