package com.project.Recommendation_Based.Telemedicine.service;

import com.itextpdf.text.DocumentException;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


public interface AppointmentService {
    public Appointment saveAppointment(Appointment appointment);
    public Appointment getAppointmentById(Integer id);
    public ByteArrayInputStream generateAppointmentReceipt(Appointment appointment) throws DocumentException, IOException;
    //List<Appointment> getUserAppointments(User user);

    List<Appointment> getUserAppointments(User user);
    //public String setPaymentStatus(String paymentStatus);

}
