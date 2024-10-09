package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {


    @Autowired
    private AppointmentRepo appointmentRepo;
    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }
}
