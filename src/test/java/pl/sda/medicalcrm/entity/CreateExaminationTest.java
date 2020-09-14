package pl.sda.medicalcrm.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class CreateExaminationTest {

    @Autowired
    private EntityManager em;

    @Test
    void createExaminationTest() {
        //given
        var picture = new Picture();
        picture.setPicturePath("www.zdjeciatusom.pl");

        List<Picture> pictures = new ArrayList<>();
        pictures.add(picture);

        var examination = new Examination();
        examination.setType("Kolonoskopia");
        examination.setResult("Mogło być lepiej");
        examination.setDescription("opis");
        examination.setPictures(pictures);

        //when
        em.persist(examination);
        em.flush();
        em.clear();
        var readExamination = em.find(Examination.class, examination.getId());

        //then
        Assertions.assertEquals(readExamination.getId(), examination.getId());
        Assertions.assertEquals(readExamination.getDescription(), examination.getDescription());
    }
}
