package pl.sda.medicalcrm.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;


@SpringBootTest
@Transactional
public class CreateClinicTest {

    @Autowired
    private EntityManager em;

    @Test
    void createClinicTest() {
        //given
        var address = new Address();
        address.setStreet("Puławska 12");
        address.setCity("Warszawa");
        address.setCountry("Poland");
        address.setZipCode("00-756");

        var clinic = new Clinic();
        clinic.setAddress(address);

        //when
        em.persist(clinic);
        em.flush();
        em.clear();
        var readClinic = em.find(Clinic.class, clinic.getId());

        //then
        Assertions.assertEquals(readClinic, clinic);
    }
}