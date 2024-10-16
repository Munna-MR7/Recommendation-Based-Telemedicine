package com.project.Recommendation_Based.Telemedicine.service;
import com.itextpdf.text.*;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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



    public Appointment getAppointmentById(Integer appointmentId) {
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
        return appointment.orElseThrow(() -> new RuntimeException("Appointment not found!"));
    }

    // Method to generate PDF for the given appointment
    public ByteArrayInputStream generateAppointmentReceipt(Appointment appointment) throws DocumentException, IOException {
        Document document = new Document(); //To Create a pdf document
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();
        document.addTitle("Appointment Reciept");// TO show pdf title

        URL imageUrl = getClass().getResource("/static/uploads/Munna_profile.jpeg");
        if (imageUrl == null) {
            throw new FileNotFoundException("Image file not found in static resources");
        }

        // Add the image to the PDF using Image.getInstance(URL)
        Image img = Image.getInstance(imageUrl);
        img.scaleToFit(150, 100);  // Set width and height
        img.setAlignment(Image.ALIGN_CENTER);
        document.add(img);

        // Add appointment details to the PDF
        document.add(new Paragraph("Appointment Confirmation Receipt"));
        document.add(new Paragraph("Name: " + appointment.getPatientName()));
        document.add(new Paragraph("Appointment Date: " + appointment.getAppointmentDate()));
        document.add(new Paragraph("Doctor: " + appointment.getDoctorName()));
        document.add(new Paragraph("Consultation Fee: " + appointment.getConsultationFee()));
        document.add(new Paragraph("Payment Status: " + appointment.getPaymentStatus()));

        String text="Thank You, ";
        Font font= FontFactory.getFont(FontFactory.COURIER_BOLD,16); //To set font and font size of paragraph
        Paragraph paragraph=new Paragraph(text + appointment.getPatientName()+ "!",font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        document.resetPageCount();
        document.addAuthor(appointment.getPatientName());
        //document.bottomMargin();
        //document.setMargins(10, 10,5,5);
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public List<Appointment> getPatientAppointments(Patient patient) {
        return appointmentRepo.findByPatient(patient);
    }



}





