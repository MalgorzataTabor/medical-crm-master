package pl.sda.medicalcrm.service;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.medicalcrm.entity.Patient;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class EmailServiceTest {

    @Autowired
    private EmailService service;

    @Autowired
    private EntityManager em;

    @Test
    void TestCreatePatientWithSendingEmail() throws MailjetSocketTimeoutException, MailjetException, NoSuchFieldException {
        //given
        var patient = new Patient();
        patient.setUsername("medicalcrm23@gmail.com");
        patient.setPassword("password1!");
        patient.setName("Jan");
        patient.setSurname("Kowalski");
        patient.setPesel("12345678912");


        //when
        service.sendEmail(patient);
        em.persist(patient);
        em.flush();
        em.clear();
        var readPatient = em.find(Patient.class, patient.getId());

        //then
        Assertions.assertEquals(readPatient, patient);

    }
}