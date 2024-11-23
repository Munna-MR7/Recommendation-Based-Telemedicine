package com.project.Recommendation_Based.Telemedicine.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.repository.AppointmentRepo;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppointmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AppointmentServiceImplDiffblueTest {
    @MockBean
    private AppointmentRepo appointmentRepo;

    @Autowired
    private AppointmentServiceImpl appointmentServiceImpl;

    /**
     * Method under test:
     * {@link AppointmentServiceImpl#findAppointmentById(Integer)}
     */
    @Test
    void testFindAppointmentById() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setAddress("42 Main St");
        doctor.setConsultationFee("Consultation Fee");
        doctor.setDegrees("Degrees");
        doctor.setDocuments("Documents");
        doctor.setEmail("jane.doe@example.org");
        doctor.setGender("Gender");
        doctor.setId(1);
        doctor.setName("Name");
        doctor.setPassword("iloveyou");
        doctor.setPhone("6625550144");
        doctor.setRegistrationDate("2020-03-01");
        doctor.setSpecialist("Specialist");

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

        Appointment appointment = new Appointment();
        appointment.setAddress("42 Main St");
        appointment.setAge("Age");
        appointment.setAppointmentDate("2020-03-01");
        appointment.setAppointmentTime("Appointment Time");
        appointment.setConsultationFee("Consultation Fee");
        appointment.setDoctor(doctor);
        appointment.setDoctorName("Doctor Name");
        appointment.setEmail("jane.doe@example.org");
        appointment.setGender("Gender");
        appointment.setId(1);
        appointment.setMedicalReport("Medical Report");
        appointment.setPatient(patient);
        appointment.setPatientName("Patient Name");
        appointment.setPaymentStatus("Payment Status");
        appointment.setPhoneNumber("6625550144");
        appointment.setRoomID("Room ID");
        appointment.setVisitStatus("Visit Status");
        Optional<Appointment> ofResult = Optional.of(appointment);
        when(appointmentRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Appointment actualFindAppointmentByIdResult = appointmentServiceImpl.findAppointmentById(1);

        // Assert
        verify(appointmentRepo).findById(eq(1));
        assertSame(appointment, actualFindAppointmentByIdResult);
    }

    /**
     * Method under test:
     * {@link AppointmentServiceImpl#findAppointmentById(Integer)}
     */
    @Test
    void testFindAppointmentById2() {
        // Arrange
        when(appointmentRepo.findById(Mockito.<Integer>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> appointmentServiceImpl.findAppointmentById(1));
        verify(appointmentRepo).findById(eq(1));
    }
}
