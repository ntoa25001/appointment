package com.example.appointment.service.impl;


import com.example.appointment.model.Medicine;
import com.example.appointment.model.Patient;
import com.example.appointment.repository.MedicineRepository;
import com.example.appointment.repository.PatientRepository;
import com.example.appointment.repository.ServiceRepository;
import com.example.appointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicineRepository medicineRepository;


    @Override
    public <S extends Patient> S save(S entity) {
        return patientRepository.save(entity);
    }

    @Override
    public Patient save1(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findById(Long aLong) {
        return patientRepository.findById(aLong);
    }

    @Override
    public boolean existsPatientByPhone(Long phone) {
        return patientRepository.existsPatientByPhone(phone);
    }

    @Override
    public boolean existsPatientByIdCard(String idCard) {
        return patientRepository.existsPatientByIdCard(idCard);
    }

    @Override
    public Patient findByPatientId(Long id) {
        return patientRepository.findByPatientId(id);
    }

    @Override
    public Patient findByEmailAndPassword(String email, String password) {
        return patientRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    @Override
    public List<Medicine> getAllMedicinePatient(Long patientId) {
        Patient patient = patientRepository.findByPatientId(patientId);
        List<Medicine> medicines = medicineRepository.findByPatientId(patient.getPatientId());
        return medicines;
    }


}
