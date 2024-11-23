package com.project.Recommendation_Based.Telemedicine.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import com.sun.security.auth.UserPrincipal;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {AppointmentController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AppointmentControllerDiffblueTest {
    @Autowired
    private AppointmentController appointmentController;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private DoctorRepo doctorRepo;

    @MockBean
    private PatientRepo patientRepo;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link AppointmentController#saveRoomID(Map)}
     */
    @Test
    void testSaveRoomID() throws Exception {
        // Arrange
        doNothing().when(appointmentService).saveRoomID(Mockito.<String>any(), Mockito.<Integer>any());

        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("roomID", "foo");
        stringStringMap.put("doctorId", "42");
        String content = (new ObjectMapper()).writeValueAsString(stringStringMap);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/appointments/saveRoomID")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Room ID saved successfully"));
    }

    /**
     * Method under test: {@link AppointmentController#saveRoomID(Map)}
     */
    @Test
    void testSaveRoomID2() throws Exception {
        // Arrange
        doNothing().when(appointmentService).saveRoomID(Mockito.<String>any(), Mockito.<Integer>any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/appointments/saveRoomID");
        postResult.characterEncoding("https://example.org/example");

        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("roomID", "foo");
        stringStringMap.put("doctorId", "42");
        String content = (new ObjectMapper()).writeValueAsString(stringStringMap);
        MockHttpServletRequestBuilder requestBuilder = postResult.contentType(MediaType.APPLICATION_JSON).content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    /**
     * Method under test: {@link AppointmentController#saveRoomID(Map)}
     */
    @Test
    void testSaveRoomID3() throws Exception {
        // Arrange
        doNothing().when(appointmentService).saveRoomID(Mockito.<String>any(), Mockito.<Integer>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/appointments/saveRoomID");
        postResult.principal(new UserPrincipal("principal"));

        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("roomID", "foo");
        stringStringMap.put("doctorId", "42");
        String content = (new ObjectMapper()).writeValueAsString(stringStringMap);
        MockHttpServletRequestBuilder requestBuilder = postResult.contentType(MediaType.APPLICATION_JSON).content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Room ID saved successfully"));
    }

    /**
     * Method under test:
     * {@link AppointmentController#doctorAppointment(Doctor, Model)}
     */
    @Test
    void testDoctorAppointment() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Appointment");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctor"))
                .andExpect(MockMvcResultMatchers.view().name("doctorAppointment"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorAppointment"));
    }

    /**
     * Method under test: {@link AppointmentController#getRoomID(Integer)}
     */
    @Test
    void testGetRoomID() throws Exception {
        // Arrange
        when(appointmentService.getRoomID(Mockito.<Integer>any())).thenReturn("Room ID");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointments/getRoomID/{appointmentId}",
                1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"roomID\":\"Room ID\"}"));
    }

    /**
     * Method under test: {@link AppointmentController#getRoomID(Integer)}
     */
    @Test
    void testGetRoomID2() throws Exception {
        // Arrange
        when(appointmentService.getRoomID(Mockito.<Integer>any())).thenReturn("Room ID");
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AppointmentController#getRoomID(Integer)}
     */
    @Test
    void testGetRoomID3() throws Exception {
        // Arrange
        when(appointmentService.getRoomID(Mockito.<Integer>any())).thenReturn("Room ID");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointments/getRoomID/{appointmentId}",
                1);
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"roomID\":\"Room ID\"}"));
    }

    /**
     * Method under test:
     * {@link AppointmentController#doctorAppointment(Doctor, Model)}
     */
    @Test
    void testDoctorAppointment2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Appointment", "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctor"))
                .andExpect(MockMvcResultMatchers.view().name("doctorAppointment"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorAppointment"));
    }

    /**
     * Method under test:
     * {@link AppointmentController#doctorAppointment(Doctor, Model)}
     */
    @Test
    void testDoctorAppointment3() throws Exception {
        // Arrange
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test:
     * {@link AppointmentController#doctorAppointment(Doctor, Model)}
     */
    @Test
    void testDoctorAppointment4() throws Exception {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Appointment");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctor", "user"))
                .andExpect(MockMvcResultMatchers.view().name("doctorAppointment"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorAppointment"));
    }

    /**
     * Method under test:
     * {@link AppointmentController#downloadAppointmentReceipt(Integer)}
     */
    @Test
    void testDownloadAppointmentReceipt() throws Exception {
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
        when(appointmentService.getAppointmentById(Mockito.<Integer>any())).thenReturn(appointment);
        when(appointmentService.generateAppointmentReceipt(Mockito.<Appointment>any()))
                .thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointment/download/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
                .andExpect(MockMvcResultMatchers.content().string("AXAXAXAX"));
    }

    /**
     * Method under test:
     * {@link AppointmentController#downloadAppointmentReceipt(Integer)}
     */
    @Test
    void testDownloadAppointmentReceipt2() throws Exception {
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
        Patient patient2 = new Patient();

        Appointment appointment = new Appointment(1, "Content-Disposition", "jane.doe@example.org", "6625550144",
                "Content-Disposition", "Content-Disposition", "42 Main St", "Content-Disposition", "Content-Disposition",
                "2020-03-01", "Content-Disposition", "Content-Disposition", "Content-Disposition", "Content-Disposition",
                "Content-Disposition", patient2, new Doctor());
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
        when(appointmentService.getAppointmentById(Mockito.<Integer>any())).thenReturn(appointment);
        when(appointmentService.generateAppointmentReceipt(Mockito.<Appointment>any()))
                .thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointment/download/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
                .andExpect(MockMvcResultMatchers.content().string("AXAXAXAX"));
    }

    /**
     * Method under test: {@link AppointmentController#payNow()}
     */
    @Test
    void testPayNow() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payNow");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Payment"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Payment"));
    }

    /**
     * Method under test: {@link AppointmentController#payNow()}
     */
    @Test
    void testPayNow2() throws Exception {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payNow");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("Payment"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Payment"));
    }
}
