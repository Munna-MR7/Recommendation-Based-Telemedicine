package com.project.Recommendation_Based.Telemedicine.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import com.project.Recommendation_Based.Telemedicine.service.HealthRecordService;
import com.project.Recommendation_Based.Telemedicine.service.PatientService;
import com.project.Recommendation_Based.Telemedicine.service.PrescriptionService;
import com.project.Recommendation_Based.Telemedicine.service.UserService;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpSession;

import java.security.Principal;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {PatientController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PatientControllerDiffblueTest {
    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private HealthRecordService healthRecordService;

    @Autowired
    private PatientController patientController;

    @MockBean
    private PatientRepo patientRepo;

    @MockBean
    private PatientService patientService;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link PatientController#commonUser(Principal, Model)}
     */
    @Test
    void testCommonUser() {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        UserPrincipal p = new UserPrincipal("p");

        // Act
        patientController.commonUser(p, new ConcurrentModel());

        // Assert that nothing has changed
        verify(userRepo).findByEmail(eq("p"));
    }

    /**
     * Method under test:
     * {@link PatientController#savePatient(Patient, HttpSession, Model)}
     */
    @Test
    void testSavePatient() throws Exception {
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
        when(patientService.savePatient(Mockito.<Patient>any())).thenReturn(patient);
        doNothing().when(userService).saveUser(Mockito.<User>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/savePatient");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Register successfully"));
    }

    /**
     * Method under test:
     * {@link PatientController#savePatient(Patient, HttpSession, Model)}
     */
    @Test
    void testSavePatient2() throws Exception {
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
        when(patientService.savePatient(Mockito.<Patient>any())).thenReturn(patient);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        doNothing().when(userService).saveUser(Mockito.<User>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/savePatient");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Register successfully"));
    }

    /**
     * Method under test:
     * {@link PatientController#pendingAppointments(Principal, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPendingAppointments() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Circular view path [patientPendingAppointments]: would dispatch back to the current handler URL [/patientPendingAppointments] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        PatientController patientController = new PatientController();
        UserPrincipal principal = new UserPrincipal("principal");

        // Act
        patientController.pendingAppointments(principal, new ConcurrentModel());
    }

    /**
     * Method under test: {@link PatientController#viewProfile(Model, Principal)}
     */
    @Test
    void testViewProfile() throws Exception {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        when(userService.getUserProfileByEmail(Mockito.<String>any())).thenReturn(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Profile");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("patientProfile"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("patientProfile"));
    }

    /**
     * Method under test: {@link PatientController#viewProfile(Model, Principal)}
     */
    @Test
    void testViewProfile2() throws Exception {
        // Arrange
        when(appointmentService.getPatientAppointments(Mockito.<Patient>any())).thenReturn(new ArrayList<>());
        when(healthRecordService.getPatientHealthRecords(Mockito.<Patient>any())).thenReturn(new ArrayList<>());

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
        when(patientService.getPatientProfile(Mockito.<String>any())).thenReturn(patient);
        when(prescriptionService.getPatientPrescriptions(Mockito.<Patient>any())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setRole("ROLE_PATIENT");
        when(userService.getUserProfileByEmail(Mockito.<String>any())).thenReturn(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Profile");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(5))
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("appointments", "healthRecords", "patient", "prescriptions", "user"))
                .andExpect(MockMvcResultMatchers.view().name("patientProfile"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("patientProfile"));
    }
}
