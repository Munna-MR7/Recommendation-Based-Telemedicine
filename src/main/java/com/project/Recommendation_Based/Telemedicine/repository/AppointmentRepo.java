package com.project.Recommendation_Based.Telemedicine.repository;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
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

    //String setPaymentStatus(String paymentStatus);
}
