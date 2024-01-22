package com.example.appointment.repository;


import com.example.appointment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
   public  boolean  existsPatientByPhone(Long phone);
   public boolean existsPatientByIdCard(String idCard);



   Patient findByPatientId(Long id);

   Patient findByEmailAndPassword(String email, String password);

   Patient findByEmail(String email);
}
