package pl.sda.medicalcrm.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SpecializationTest {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void testCreateSpecialization(){
        //given
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

        //when
        em.persist(specialization);
        em.flush();
        em.clear();
        var readSpecialization = em.find(Specialization.class, specialization.getId());

        //then
        assertEquals(readSpecialization.getId(), specialization.getId());
    }

}
