package com.project.Recommendation_Based.Telemedicine.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

@ContextConfiguration(classes = {DoctorServiceImpl.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DoctorServiceImplDiffblueTest {
    @MockBean
    private DoctorRepo doctorRepo;

    @Autowired
    private DoctorServiceImpl doctorServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link DoctorServiceImpl#getDoctors()}
     */
    @Test
    void testGetDoctors() {
        // Arrange
        ArrayList<Doctor> doctorList = new ArrayList<>();
        when(doctorRepo.findAll()).thenReturn(doctorList);

        // Act
        List<Doctor> actualDoctors = doctorServiceImpl.getDoctors();

        // Assert
        verify(doctorRepo).findAll();
        assertTrue(actualDoctors.isEmpty());
        assertSame(doctorList, actualDoctors);
    }

    /**
     * Method under test: {@link DoctorServiceImpl#searchDoctor(Integer)}
     */
    @Test
    void testSearchDoctor() {
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
        Optional<Doctor> ofResult = Optional.of(doctor);
        when(doctorRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Doctor actualSearchDoctorResult = doctorServiceImpl.searchDoctor(1);

        // Assert
        verify(doctorRepo).findById(eq(1));
        assertSame(doctor, actualSearchDoctorResult);
    }

    /**
     * Method under test: {@link DoctorServiceImpl#removeSessionMessage()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveSessionMessage() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.context.request.ServletRequestAttributes.getRequest()" because the return value of "org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()" is null
        //       at com.project.Recommendation_Based.Telemedicine.service.DoctorServiceImpl.removeSessionMessage(DoctorServiceImpl.java:43)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        doctorServiceImpl.removeSessionMessage();
    }

    /**
     * Method under test: {@link DoctorServiceImpl#saveDoctor(Doctor)}
     */
    @Test
    void testSaveDoctor() {
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
        when(doctorRepo.save(Mockito.<Doctor>any())).thenReturn(doctor);

        Doctor doctor2 = new Doctor();
        doctor2.setAddress("42 Main St");
        doctor2.setConsultationFee("Consultation Fee");
        doctor2.setDegrees("Degrees");
        doctor2.setDocuments("Documents");
        doctor2.setEmail("jane.doe@example.org");
        doctor2.setGender("Gender");
        doctor2.setId(1);
        doctor2.setName("Name");
        doctor2.setPassword("iloveyou");
        doctor2.setPhone("6625550144");
        doctor2.setRegistrationDate("2020-03-01");
        doctor2.setSpecialist("Specialist");

        // Act
        doctorServiceImpl.saveDoctor(doctor2);

        // Assert
        verify(doctorRepo).save(isA(Doctor.class));
    }

    /**
     * Method under test: {@link DoctorServiceImpl#getDoctorByEmail(String)}
     */
    @Test
    void testGetDoctorByEmail() {
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
        when(doctorRepo.findByEmail(Mockito.<String>any())).thenReturn(doctor);

        // Act
        Doctor actualDoctorByEmail = doctorServiceImpl.getDoctorByEmail("jane.doe@example.org");

        // Assert
        verify(doctorRepo).findByEmail(eq("jane.doe@example.org"));
        assertSame(doctor, actualDoctorByEmail);
    }
}
