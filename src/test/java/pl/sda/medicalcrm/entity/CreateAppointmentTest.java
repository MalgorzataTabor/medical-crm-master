package pl.sda.medicalcrm.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class CreateAppointmentTest {

    @Autowired
    private EntityManager em;

    @Test
    void createAppointmentTest() {
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

        LocalDateTime date = LocalDateTime.now();

        var clinic = new Clinic();
        clinic.setAddress(address);

        var prescription = new Prescription();
        prescription.setDescription("brac prochy");
        prescription.setPrescriptionNo(1234);

        var appointment = new Appointment();
        appointment.setAppointmentDate(date);
        appointment.setClinic(clinic);
        appointment.setOnline(false);
        appointment.setPrescription(prescription);
        appointment.setSpecialization(specialization);
        appointment.setUser(patient);

        //when
        em.persist(appointment);
        em.flush();
        em.clear();
        var readAppointment = em.find(Appointment.class,appointment.getId());

        //then
        Assertions.assertEquals(readAppointment.getId(), appointment.getId());
        }
}
