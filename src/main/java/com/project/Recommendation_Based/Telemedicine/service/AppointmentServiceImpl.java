package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {


    @Autowired
    private AppointmentRepo appointmentRepo;
    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }



    public Appointment getAppointmentById(Integer id) {
        Optional<Appointment> appointment = appointmentRepo.findById(id);
        return appointment.orElseThrow(() -> new RuntimeException("Appointment not found!"));
    }

    // Method to generate PDF for the given appointment
    public ByteArrayInputStream generateAppointmentReceipt(Appointment appointment) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        // Add appointment details to the PDF
        document.add(new Paragraph("Appointment Confirmation Receipt"));
        document.add(new Paragraph("Name: " + appointment.getPatientName()));
        document.add(new Paragraph("Appointment Date: " + appointment.getAppointmentDate()));
        document.add(new Paragraph("Doctor: " + appointment.getDoctorName()));
        document.add(new Paragraph("Consultation Fee: " + appointment.getConsultationFee()));

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public List<Appointment> getUserAppointments(User user) {
        return appointmentRepo.findByUser(user);
    }

}





