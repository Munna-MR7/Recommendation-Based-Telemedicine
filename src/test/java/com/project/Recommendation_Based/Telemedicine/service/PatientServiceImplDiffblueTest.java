package com.project.Recommendation_Based.Telemedicine.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PatientServiceImpl.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PatientServiceImplDiffblueTest {
    @MockBean
    private PatientRepo patientRepo;

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    /**
     * Method under test: {@link PatientServiceImpl#savePatient(Patient)}
     */
    @Test
    void testSavePatient() {
        // Arrange
        Patient patient = new Patient();
        patient.setAddress("42 Main St");
        patient.setAppointments(new ArrayList<>());
        patient.setAssignedDoctors(new ArrayList<>());
        patient.setDateOfBirth("2020-03-01");
        patient.setEmail("jane.doe@example.org");
        patient.setHealthRecords(new ArrayList<>());
        patient.setId(1);
        patient.setName("Name");
        patient.setPassword("iloveyou");
        patient.setPayments(new ArrayList<>());
        patient.setPhoneNumber("6625550144");
        patient.setPrescriptions(new ArrayList<>());
        patient.setProfilePicturePath("Profile Picture Path");
        patient.setRole("Role");
        when(patientRepo.save(Mockito.<Patient>any())).thenReturn(patient);

        Patient patient2 = new Patient();
        patient2.setAddress("42 Main St");
        patient2.setAppointments(new ArrayList<>());
        patient2.setAssignedDoctors(new ArrayList<>());
        patient2.setDateOfBirth("2020-03-01");
        patient2.setEmail("jane.doe@example.org");
        patient2.setHealthRecords(new ArrayList<>());
        patient2.setId(1);
        patient2.setName("Name");
        patient2.setPassword("iloveyou");
        patient2.setPayments(new ArrayList<>());
        patient2.setPhoneNumber("6625550144");
        patient2.setPrescriptions(new ArrayList<>());
        patient2.setProfilePicturePath("Profile Picture Path");
        patient2.setRole("Role");

        // Act
        Patient actualSavePatientResult = patientServiceImpl.savePatient(patient2);

        // Assert
        verify(patientRepo).save(isA(Patient.class));
        assertSame(patient, actualSavePatientResult);
    }

    /**
     * Method under test: {@link PatientServiceImpl#removeSessionMessage()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveSessionMessage() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.context.request.ServletRequestAttributes.getRequest()" because the return value of "org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()" is null
        //       at com.project.Recommendation_Based.Telemedicine.service.PatientServiceImpl.removeSessionMessage(PatientServiceImpl.java:37)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        patientServiceImpl.removeSessionMessage();
    }

    /**
     * Method under test: {@link PatientServiceImpl#getPatientProfile(String)}
     */
    @Test
    void testGetPatientProfile() {
        // Arrange
        Patient patient = new Patient();
        patient.setAddress("42 Main St");
        patient.setAppointments(new ArrayList<>());
        patient.setAssignedDoctors(new ArrayList<>());
        patient.setDateOfBirth("2020-03-01");
        patient.setEmail("jane.doe@example.org");
        patient.setHealthRecords(new ArrayList<>());
        patient.setId(1);
        patient.setName("Name");
        patient.setPassword("iloveyou");
        patient.setPayments(new ArrayList<>());
        patient.setPhoneNumber("6625550144");
        patient.setPrescriptions(new ArrayList<>());
        patient.setProfilePicturePath("Profile Picture Path");
        patient.setRole("Role");
        when(patientRepo.findByEmail(Mockito.<String>any())).thenReturn(patient);

        // Act
        Patient actualPatientProfile = patientServiceImpl.getPatientProfile("jane.doe@example.org");

        // Assert
        verify(patientRepo).findByEmail(eq("jane.doe@example.org"));
        assertSame(patient, actualPatientProfile);
    }

    /**
     * Method under test: {@link PatientServiceImpl#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
        // Arrange, Act and Assert
        assertNull(patientServiceImpl.findByEmail("jane.doe@example.org"));
    }
}
