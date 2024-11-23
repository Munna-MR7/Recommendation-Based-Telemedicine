package com.project.Recommendation_Based.Telemedicine.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorRequestService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpSession;

import java.security.Principal;
import java.util.ArrayList;

import org.apache.catalina.connector.CoyotePrincipal;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {DoctorController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DoctorControllerDiffblueTest {
    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private DoctorController doctorController;

    @MockBean
    private DoctorRepo doctorRepo;

    @MockBean
    private DoctorRequestService doctorRequestService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link DoctorController#commonUser(Principal, Model)}
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
        doctorController.commonUser(p, new ConcurrentModel());

        // Assert that nothing has changed
        verify(userRepo).findByEmail(eq("p"));
    }

    /**
     * Method under test: {@link DoctorController#commonUser(Principal, Model)}
     */
    @Test
    void testCommonUser2() {
        // Arrange
        CoyotePrincipal p = mock(CoyotePrincipal.class);
        when(p.getName()).thenThrow(new IllegalStateException("foo"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> doctorController.commonUser(p, new ConcurrentModel()));
        verify(p).getName();
    }

    /**
     * Method under test:
     * {@link DoctorController#saveDoctor(Doctor, HttpSession, Model)}
     */
    @Test
    void testSaveDoctor() throws Exception {
        // Arrange
        doNothing().when(doctorService).saveDoctor(Mockito.<Doctor>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveDoctor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success"));
    }

    /**
     * Method under test:
     * {@link DoctorController#saveDoctor(Doctor, HttpSession, Model)}
     */
    @Test
    void testSaveDoctor2() throws Exception {
        // Arrange
        doNothing().when(doctorService).saveDoctor(Mockito.<Doctor>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveDoctor");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success"));
    }

    /**
     * Method under test: {@link DoctorController#doctorLoginPage()}
     */
    @Test
    void testDoctorLoginPage() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctorLoginPage");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("doctorLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorLogin"));
    }

    /**
     * Method under test: {@link DoctorController#getDoctors(Model, HttpSession)}
     */
    @Test
    void testGetDoctors() throws Exception {
        // Arrange
        when(doctorService.getDoctors()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patient/allDoctor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctors", "session"))
                .andExpect(MockMvcResultMatchers.view().name("doctorLists"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorLists"));
    }

    /**
     * Method under test: {@link DoctorController#getDoctors(Model, HttpSession)}
     */
    @Test
    void testGetDoctors2() throws Exception {
        // Arrange
        when(doctorService.getDoctors()).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patient/allDoctor");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctors", "session", "user"))
                .andExpect(MockMvcResultMatchers.view().name("doctorLists"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorLists"));
    }

    /**
     * Method under test: {@link DoctorController#pendingAppointments(Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPendingAppointments() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because "authentication" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because "authentication" is null
        //       at com.project.Recommendation_Based.Telemedicine.controller.DoctorController.pendingAppointments(DoctorController.java:105)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        DoctorController doctorController = new DoctorController();

        // Act
        doctorController.pendingAppointments(new ConcurrentModel());
    }

    /**
     * Method under test: {@link DoctorController#doctorLoginPage()}
     */
    @Test
    void testDoctorLoginPage2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctorLoginPage", "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("doctorLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorLogin"));
    }

    /**
     * Method under test: {@link DoctorController#doctorLoginPage()}
     */
    @Test
    void testDoctorLoginPage3() throws Exception {
        // Arrange
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link DoctorController#doctorLoginPage()}
     */
    @Test
    void testDoctorLoginPage4() throws Exception {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctorLoginPage");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("doctorLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorLogin"));
    }

    /**
     * Method under test: {@link DoctorController#doctorRequest(DoctorRequest)}
     */
    @Test
    void testDoctorRequest() throws Exception {
        // Arrange
        doNothing().when(doctorRequestService).saveDoctorRequest(Mockito.<DoctorRequest>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/doctorRequest");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Request Pending"));
    }

    /**
     * Method under test: {@link DoctorController#doctorRequest(DoctorRequest)}
     */
    @Test
    void testDoctorRequest2() throws Exception {
        // Arrange
        doNothing().when(doctorRequestService).saveDoctorRequest(Mockito.<DoctorRequest>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/doctorRequest");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Request Pending"));
    }

    /**
     * Method under test: {@link DoctorController#getDoctor(Model)}
     */
    @Test
    void testGetDoctor() throws Exception {
        // Arrange
        when(doctorService.getDoctors()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allDoctor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctors"))
                .andExpect(MockMvcResultMatchers.view().name("doctorLists"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorLists"));
    }

    /**
     * Method under test: {@link DoctorController#getDoctor(Model)}
     */
    @Test
    void testGetDoctor2() throws Exception {
        // Arrange
        when(doctorService.getDoctors()).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allDoctor");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctors", "user"))
                .andExpect(MockMvcResultMatchers.view().name("doctorLists"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorLists"));
    }

    /**
     * Method under test: {@link DoctorController#getDocument(int)}
     */
    @Test
    void testGetDocument() throws Exception {
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
        when(doctorService.searchDoctor(Mockito.<Integer>any())).thenReturn(doctor);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/{id}/documents", "Uri Variables",
                "Uri Variables");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link DoctorController#getDocument(int)}
     */
    @Test
    void testGetDocument2() throws Exception {
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
        when(doctorService.searchDoctor(Mockito.<Integer>any())).thenReturn(doctor);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/{id}/documents", "Uri Variables",
                "Uri Variables");
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link DoctorController#markVisited(Integer)}
     */
    @Test
    void testMarkVisited() throws Exception {
        // Arrange
        doNothing().when(appointmentService).patientMarkVisited(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/markVisited/{appointmentId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Patient marked visited!"));
    }

    /**
     * Method under test: {@link DoctorController#markVisited(Integer)}
     */
    @Test
    void testMarkVisited2() throws Exception {
        // Arrange
        doNothing().when(appointmentService).patientMarkVisited(Mockito.<Integer>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/markVisited/{appointmentId}", 1);
        requestBuilder.principal(new UserPrincipal("principal"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Patient marked visited!"));
    }
}
