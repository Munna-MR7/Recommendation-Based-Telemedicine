package com.project.Recommendation_Based.Telemedicine.service;

import com.itextpdf.text.DocumentException;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


public interface AppointmentService {
    public Appointment saveAppointment(Appointment appointment);
    public Appointment getAppointmentById(Integer appointmentId);
    public ByteArrayInputStream generateAppointmentReceipt(Appointment appointment) throws DocumentException, IOException;

    List<Appointment> getPatientAppointments(Patient patient);

    List<Appointment> showPendingAppointments(int doctorId);

    //public String setPaymentStatus(String paymentStatus);

}
