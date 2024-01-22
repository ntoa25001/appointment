package com.example.appointment.repository;

import com.example.appointment.model.Appointment;
import com.example.appointment.model.Medicine;
import com.example.appointment.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Page<Medicine> findByNameContaining(String name, Pageable pageable);

    @Query(value = "select medicine.* from medicine join patient on medicine.patient_id = patient.patient_id where medicine.patient_id=?1", nativeQuery = true)
    List<Medicine> findByPatientId(Long patientId);

}
