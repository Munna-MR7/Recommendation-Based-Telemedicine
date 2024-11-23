package com.project.Recommendation_Based.Telemedicine.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
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

@ContextConfiguration(classes = {UserServiceImpl.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserServiceImplDiffblueTest {
    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#saveUser(User)}
     */
    @Test
    void testSaveUser() {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setRole("Role");

        // Act
        userServiceImpl.saveUser(user2);

        // Assert
        verify(userRepo).save(isA(User.class));
    }

    /**
     * Method under test: {@link UserServiceImpl#removeSessionMessage()}
     */

    /**
     * Method under test: {@link UserServiceImpl#getUserProfileByEmail(String)}
     */
    @Test
    void testGetUserProfileByEmail() {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        when(userRepo.findByEmail(Mockito.<String>any())).thenReturn(user);

        // Act
        User actualUserProfileByEmail = userServiceImpl.getUserProfileByEmail("jane.doe@example.org");

        // Assert
        verify(userRepo).findByEmail(eq("jane.doe@example.org"));
        assertSame(user, actualUserProfileByEmail);
    }
}//package com.project.Recommendation_Based.Telemedicine.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.project.Recommendation_Based.Telemedicine.entity.User;
//import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.aot.DisabledInAotMode;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {PatientServiceImpl.class, BCryptPasswordEncoder.class})
//@ExtendWith(SpringExtension.class)
//@DisabledInAotMode
//class UserServiceImplDiffblueTest {
//    @MockBean
//    private UserRepo userRepo;
//
//    @Autowired
//    private PatientServiceImpl userServiceImpl;
//
//    /**
//     * Method under test: {@link PatientServiceImpl#saveUser(User)}
//     */
//    @Test
//    void testSaveUser() {
//        // Arrange
//        User user = new User();
//        user.setEmail("jane.doe@example.org");
//        user.setId(1);
//
//        user.setPassword("xyz");
//        user.setRole("Role");
//        when(userRepo.save(Mockito.<User>any())).thenReturn(user);
//
//        User user2 = new User();
//        user2.setEmail("jane.doe@example.org");
//        user2.setId(1);
//        user2.setPassword("xyzz");
//        user2.setRole("Role");
//
//        // Act
//        User actualSaveUserResult = userServiceImpl.saveUser(user2);
//
//        // Assert
//        verify(userRepo).save(isA(User.class));
//        assertEquals("ROLE_USER", user2.getRole());
//        assertSame(user, actualSaveUserResult);
//    }
//}
