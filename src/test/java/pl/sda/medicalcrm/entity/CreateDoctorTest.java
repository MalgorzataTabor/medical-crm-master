package pl.sda.medicalcrm.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CreateDoctorTest {
    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void testCreateDoctor() {

        //given

        var doctor = new Doctor();
        doctor.setUsername("test@test.com.pl");
        doctor.setPassword("Password1!");
        doctor.setNpwz("1234567");
        doctor.setName("Jan");
        doctor.setSurname("Kowalski");

        //when
        em.persist(doctor);
        em.flush();
        em.clear();

        var readDoctor = em.find(Doctor.class, doctor.getId());

        //then
        assertEquals(readDoctor, doctor);
    }

//    @Test
//    @Transactional
//    void testExpectedException() {
//
//        //given
//
//        var doctor = new Doctor("doctor", "Passwor1", "1234566",
//                "Damian", "Nuta1");
//
//        //then
//
//        Assertions.assertThrows(ConstraintViolationException.class, () -> {
//            em.persist(doctor);
//            em.flush();
//            em.clear();
//        });
//
//    }

}