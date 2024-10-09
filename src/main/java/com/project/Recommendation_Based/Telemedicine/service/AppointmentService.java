package com.project.Recommendation_Based.Telemedicine.service;

import com.itextpdf.text.DocumentException;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface AppointmentService {
    public Appointment saveAppointment(Appointment appointment);
    public Appointment getAppointmentById(Integer id);
    public ByteArrayInputStream generateAppointmentReceipt(Appointment appointment) throws DocumentException, IOException;

}
