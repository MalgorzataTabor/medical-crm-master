package pl.sda.medicalcrm.service;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.medicalcrm.entity.*;
import pl.sda.medicalcrm.repository.AppointmentRepository;

import javax.persistence.*;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class AppointmentServiceTest {

    @Autowired
    private AppointmentService service;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void testCreateAppointment() throws MailjetSocketTimeoutException, MailjetException {
        //given
        var patient = new Patient();
        patient.setUsername("medicalcrm23@gmail.com");
        patient.setPassword("Password1!");
        patient.setName("Jan");
        patient.setSurname("Kowalski");
        patient.setPesel("12345678978");

        var doctor = new Doctor();
        doctor.setSurname("Nowak");
        doctor.setName("Karol");
        doctor.setNpwz("1234567");
        doctor.setPassword("Password2!");
        doctor.setUsername("test@test.com.pl");

        var specialization = new Specialization();
        specialization.setTypeOfSpecialization("Kardiolog");
        List<User> doctors = new ArrayList<>();
        doctors.add(doctor);
        specialization.setDoctors(doctors);

        var address = new Address();
        address.setStreet("Puławska 12");
        address.setCity("Warszawa");
        address.setCountry("Poland");
        address.setZipCode("00-756");

        var clinic = new Clinic();
        clinic.setAddress(address);

        var prescription = new Prescription();
        prescription.setDescription("brac prochy");
        prescription.setPrescriptionNo(1234);

        var date = LocalDateTime.now();
        var appointment = new Appointment();
        appointment.setAppointmentDate(date);
        appointment.setClinic(clinic);
        appointment.setOnline(false);
        appointment.setPrescription(prescription);
        appointment.setSpecialization(specialization);
        appointment.setUser(patient);

        //when
        emailService.sendEmail(patient);
        repository.save(appointment);
        em.flush();
        em.clear();

        //then
        var readAppointment = repository.findById(appointment.getId());

        assertTrue(readAppointment.isPresent());
        assertEquals(appointment.getUser().getId(),patient.getId());
        assertEquals(readAppointment.get().getId(), appointment.getId());








    }
}