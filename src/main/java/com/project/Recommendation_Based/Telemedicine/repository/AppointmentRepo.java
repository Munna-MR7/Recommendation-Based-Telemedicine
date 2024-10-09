package com.project.Recommendation_Based.Telemedicine.repository;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {


}
