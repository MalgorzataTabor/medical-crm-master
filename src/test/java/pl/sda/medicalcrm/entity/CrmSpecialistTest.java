package pl.sda.medicalcrm.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class CrmSpecialistTest {

    @Autowired
    private EntityManager em;

    @Test
    void CreateCrmSpecialistTest (){
        //given
        var crmSpecialist = new CrmSpecialist();
        crmSpecialist.setUsername("test@test.com.pl");
        crmSpecialist.setPassword("Password1!");
        crmSpecialist.setName("Jan");
        crmSpecialist.setSurname("Kowalski");

        //when
        em.persist(crmSpecialist);
        em.flush();
        em.clear();
        var readCrmSpecialist = em.find(CrmSpecialist.class, crmSpecialist.getId());

        //then
        Assertions.assertEquals(readCrmSpecialist, crmSpecialist);
    }
}
