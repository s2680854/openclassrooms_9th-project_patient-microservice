package com.mediscreen.model;

import com.mediscreen.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void patientTest() {

        Patient patient = new Patient();
        patient.setRiskLevel("None");
        patient.setFullName("Harry POTTER");
        patient.setGender("M");
        patient.setBirthDate(LocalDate.now().minusYears(12));
        patient.setAddress("4, Privet Drive, Little Whinging");
        patient.setPhone("791-112-3456");

        /**
         * Create
         */
        Patient retrievedPatient = patientRepository.save(patient);
        patient.setPatientId(retrievedPatient.getPatientId());
        assertEquals("Harry POTTER", retrievedPatient.getFullName());
        assertEquals("M", retrievedPatient.getGender());
        assertEquals(LocalDate.now().minusYears(12), retrievedPatient.getBirthDate());
        assertEquals("4, Privet Drive, Little Whinging", retrievedPatient.getAddress());

        /**
         * Read
         */

        Patient newPatient = new Patient("None", "Harry POTTER", "M", LocalDate.now().minusYears(12),
                "4, Privet Drive, Little Whinging", "791-112-3456");
        patientRepository.save(newPatient);
        List<Patient> listResult = patientRepository.findAll();
        assertTrue(listResult.size() > 0);

        /**
         * Update
         */
        patient.setPhone("791-224-2487");
        patient = patientRepository.save(patient);
        assertEquals("791-224-2487", patient.getPhone());

        /**
         * Delete
         */
        patientRepository.delete(patient);
        Optional<Patient> patientList = patientRepository.findById(patient.getPatientId());
        assertFalse(patientList.isPresent());

        /**
         * Completing 100% coverage
         */
        Patient somePatient = new Patient("None", "Ron WEASLEY", "M", LocalDate.now().minusYears(12),
                "The Borrow, Ottery St. Catchpole", "791-145-6752");
        assertTrue(somePatient.equals(somePatient)); // identical objects case
        assertFalse(somePatient.equals(null)); // null object case
    }
}
