package com.project.Recommendation_Based.Telemedicine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRequestRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DoctorRequestServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DoctorRequestServiceImplDiffblueTest {
    @MockBean
    private DoctorRequestRepo doctorRequestRepo;

    @Autowired
    private DoctorRequestServiceImpl doctorRequestServiceImpl;

    /**
     * Method under test:
     * {@link DoctorRequestServiceImpl#saveDoctorRequest(DoctorRequest)}
     */
    @Test
    void testSaveDoctorRequest() {
        // Arrange
        DoctorRequest doctorRequest = new DoctorRequest();
        doctorRequest.setAddress("42 Main St");
        doctorRequest.setConsultationFee("Consultation Fee");
        doctorRequest.setDegrees("Degrees");
        doctorRequest.setDocuments("Documents");
        doctorRequest.setEmail("jane.doe@example.org");
        doctorRequest.setGender("Gender");
        doctorRequest.setId(1);
        doctorRequest.setName("Name");
        doctorRequest.setPassword("iloveyou");
        doctorRequest.setPhone("6625550144");
        doctorRequest.setRegistrationDate("2020-03-01");
        doctorRequest.setSpecialist("Specialist");
        when(doctorRequestRepo.save(Mockito.<DoctorRequest>any())).thenReturn(doctorRequest);

        DoctorRequest doctorRequest2 = new DoctorRequest();
        doctorRequest2.setAddress("42 Main St");
        doctorRequest2.setConsultationFee("Consultation Fee");
        doctorRequest2.setDegrees("Degrees");
        doctorRequest2.setDocuments("Documents");
        doctorRequest2.setEmail("jane.doe@example.org");
        doctorRequest2.setGender("Gender");
        doctorRequest2.setId(1);
        doctorRequest2.setName("Name");
        doctorRequest2.setPassword("iloveyou");
        doctorRequest2.setPhone("6625550144");
        doctorRequest2.setRegistrationDate("2020-03-01");
        doctorRequest2.setSpecialist("Specialist");

        // Act
        doctorRequestServiceImpl.saveDoctorRequest(doctorRequest2);

        // Assert that nothing has changed
        verify(doctorRequestRepo).save(isA(DoctorRequest.class));
        assertEquals("2020-03-01", doctorRequest2.getRegistrationDate());
        assertEquals("42 Main St", doctorRequest2.getAddress());
        assertEquals("6625550144", doctorRequest2.getPhone());
        assertEquals("Consultation Fee", doctorRequest2.getConsultationFee());
        assertEquals("Degrees", doctorRequest2.getDegrees());
        assertEquals("Documents", doctorRequest2.getDocuments());
        assertEquals("Gender", doctorRequest2.getGender());
        assertEquals("Name", doctorRequest2.getName());
        assertEquals("Specialist", doctorRequest2.getSpecialist());
        assertEquals("iloveyou", doctorRequest2.getPassword());
        assertEquals("jane.doe@example.org", doctorRequest2.getEmail());
        assertEquals(1, doctorRequest2.getId());
    }

    /**
     * Method under test: {@link DoctorRequestServiceImpl#showAllPendingRequest()}
     */
    @Test
    void testShowAllPendingRequest() {
        // Arrange
        ArrayList<DoctorRequest> doctorRequestList = new ArrayList<>();
        when(doctorRequestRepo.findAll()).thenReturn(doctorRequestList);

        // Act
        List<DoctorRequest> actualShowAllPendingRequestResult = doctorRequestServiceImpl.showAllPendingRequest();

        // Assert
        verify(doctorRequestRepo).findAll();
        assertTrue(actualShowAllPendingRequestResult.isEmpty());
        assertSame(doctorRequestList, actualShowAllPendingRequestResult);
    }

    /**
     * Method under test: {@link DoctorRequestServiceImpl#searchDoctorById(Integer)}
     */
    @Test
    void testSearchDoctorById() {
        // Arrange
        DoctorRequest doctorRequest = new DoctorRequest();
        doctorRequest.setAddress("42 Main St");
        doctorRequest.setConsultationFee("Consultation Fee");
        doctorRequest.setDegrees("Degrees");
        doctorRequest.setDocuments("Documents");
        doctorRequest.setEmail("jane.doe@example.org");
        doctorRequest.setGender("Gender");
        doctorRequest.setId(1);
        doctorRequest.setName("Name");
        doctorRequest.setPassword("iloveyou");
        doctorRequest.setPhone("6625550144");
        doctorRequest.setRegistrationDate("2020-03-01");
        doctorRequest.setSpecialist("Specialist");
        Optional<DoctorRequest> ofResult = Optional.of(doctorRequest);
        when(doctorRequestRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        DoctorRequest actualSearchDoctorByIdResult = doctorRequestServiceImpl.searchDoctorById(1);

        // Assert
        verify(doctorRequestRepo).findById(eq(1));
        assertSame(doctorRequest, actualSearchDoctorByIdResult);
    }

    /**
     * Method under test:
     * {@link DoctorRequestServiceImpl#deleteDoctorRequest(Integer)}
     */
    @Test
    void testDeleteDoctorRequest() {
        // Arrange
        doNothing().when(doctorRequestRepo).deleteById(Mockito.<Integer>any());

        // Act
        doctorRequestServiceImpl.deleteDoctorRequest(1);

        // Assert that nothing has changed
        verify(doctorRequestRepo).deleteById(eq(1));
    }
}
