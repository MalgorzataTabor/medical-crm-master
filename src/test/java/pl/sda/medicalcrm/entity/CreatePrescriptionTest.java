package pl.sda.medicalcrm.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class CreatePrescriptionTest {

    @Autowired
    private EntityManager em;

    @Test
    void CreatePrescriptionTest() {

    //given
        var prescription = new Prescription();
        prescription.setDescription("brac prochy");
        prescription.setPrescriptionNo(1234);

    //when
        em.persist(prescription);
        em.flush();
        em.clear();
    var readPrescription = em.find(Prescription.class,prescription.getId());

    //then
        Assertions.assertEquals(readPrescription.getId(), prescription.getId());
}
}
