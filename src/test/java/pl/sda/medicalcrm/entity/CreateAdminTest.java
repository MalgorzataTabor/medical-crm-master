package pl.sda.medicalcrm.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class CreateAdminTest {

    @Autowired
    private EntityManager em;

    @Test
    void createAdminTest(){
        //given
        var admin = new Admin();
        admin.setUsername("test@test.com.pl");
        admin.setPassword("Password1!");
        admin.setName("Jan");
        admin.setSurname("Kowalski");

        //when
        em.persist(admin);
        em.flush();
        em.clear();
        var readAdmin = em.find(Admin.class, admin.getId());

        //then
        Assertions.assertEquals(readAdmin, admin);

    }
}
