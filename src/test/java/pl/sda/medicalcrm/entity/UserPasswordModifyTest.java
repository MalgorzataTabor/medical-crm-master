//package pl.sda.medicalcrm.entity;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.sda.medicalcrm.repository.UserRepository;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Transactional
//public class UserPasswordModifyTest {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Autowired
//    private EntityManager em;
//
//    @Test
//    void testPatientPasswordModify() {
//        //given
//        var patient = new Patient("login", "password", "Jan", "Kowalski", "123456789");
//        repository.saveAndFlush(patient);
//        em.clear();
//
//        //when
//        int updated = repository.updatePatientPassword(patient.getId(), "newPassword");
//        repository.flush();
//
//        //then
//        assertEquals(1, updated);
//        var user = repository.findById(patient.getId()).get();
//        assertEquals("newPassword", user.getPassword());
//    }
//
//    @Test
//    void testDoctorPasswordModify() {
//        //given
//        var doctor = new Doctor("login", "password", "1234567", "Jan", "Kowalski");
//        repository.saveAndFlush(doctor);
//        em.clear();
//
//        //when
//        int updated = repository.updateDoctorPassword(doctor.getId(), "newDoctorPassword");
//        repository.flush();
//
//        //then
//        assertEquals(1, updated);
//        var user = repository.findById(doctor.getId()).get();
//        assertEquals("newDoctorPassword", user.getPassword());
//    }
//
//    @Test
//    void testCrmSpecialistPasswordModify() {
//        //given
//        var crm = new CrmSpecialist("login", "password", "Jan", "Kowalski");
//        repository.saveAndFlush(crm);
//        em.clear();
//
//        //when
//        int updated = repository.updateCrmSpecialistPassword(crm.getId(), "newCrmSpecialistPassword");
//        repository.flush();
//
//        //then
//        assertEquals(1, updated);
//        var user = repository.findById(crm.getId()).get();
//        assertEquals("newCrmSpecialistPassword", user.getPassword());
//    }
//}