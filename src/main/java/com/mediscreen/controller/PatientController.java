package com.mediscreen.controller;

import com.mediscreen.model.Patient;
import com.mediscreen.service.PatientCreationService;
import com.mediscreen.service.PatientDeletionService;
import com.mediscreen.service.PatientReadService;
import com.mediscreen.service.PatientUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PatientController {

    @Autowired
    private PatientCreationService patientCreationService;
    @Autowired
    private PatientReadService patientReadService;
    @Autowired
    private PatientUpdateService patientUpdateService;
    @Autowired
    private PatientDeletionService patientDeletionService;

    @PostMapping("/patient/add")
    public Patient addPatient(@RequestBody Patient patient) {

        return patientCreationService.createPatient(patient);
    }

    @PostMapping("/patientList/add")
    public Collection<Patient> addPatientList(@RequestBody Collection<Patient> patientList) {

        return patientCreationService.createPatientList(patientList);
    }

    @GetMapping("/patient/{patientId}")
    public Patient readPatientById(@PathVariable Long patientId) {

        return patientReadService.readPatientById(patientId);
    }

    @GetMapping("/patientList")
    public Collection<Patient> readPatientList() {

        return patientReadService.readPatientList();
    }

    @PutMapping("/patient/update")
    public Patient updatePatient(@RequestBody Patient patient) {

        return patientUpdateService.updatePatient(patient);
    }

    @DeleteMapping("/patient/{patientId}")
    public void deletePatientById(@PathVariable Long patientId) {

        patientDeletionService.deletePatientById(patientId);
    }

    @DeleteMapping("/patientList")
    public void deletePatientList() {

        patientDeletionService.deletePatientList();
    }
}