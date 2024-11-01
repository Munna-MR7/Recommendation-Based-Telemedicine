package com.project.Recommendation_Based.Telemedicine.repository;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatient(Patient patient);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.visitStatus = 'Unvisited'")
    List<Appointment> findUnvisitedAppointmentByDoctorId(@Param("doctorId") int doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId AND a.visitStatus = 'Unvisited'")
    List<Appointment> findUnvisitedAppointmentByPatientId(@Param("patientId") int patientId);

    @Query("SELECT a.roomID FROM Appointment a WHERE a.id = :appointmentId")
    String findRoomIDByAppointmentId(@Param("appointmentId") int appointmentId);

    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.roomID = :roomID WHERE a.id= :appointmentId")
    void upDateRoomIdByAppointmentId(String roomID, Integer appointmentId);

    //String setPaymentStatus(String paymentStatus);
}
