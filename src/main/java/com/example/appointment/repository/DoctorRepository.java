package com.example.appointment.repository;


import com.example.appointment.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByNameContaining(String name, Pageable pageable);
    Doctor findByDoctorId(Long id);

    @Query(value = "SELECT doctor.* FROM doctor JOIN specialist ON doctor.specialist_id = specialist.specialist_id JOIN time ON doctor.doctor_id = time.doctor_id WHERE time.period = ?1 AND specialist.specialist_id = ?2" , nativeQuery = true)
    List<Doctor> getDoctorTime(String period, Long specialistId);


}
