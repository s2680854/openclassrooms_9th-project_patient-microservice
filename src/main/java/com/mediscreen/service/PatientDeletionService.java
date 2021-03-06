package com.mediscreen.service;

import com.mediscreen.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientDeletionService {

    @Autowired
    private PatientRepository patientRepository;

    public void deletePatientById(Long patientId) {

        if(patientRepository.existsById(patientId)) {
            patientRepository.deleteById(patientId);
        }
    }

    public void deletePatientList() {

        patientRepository.deleteAll();
    }
}
