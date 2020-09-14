//package pl.sda.medicalcrm.service.specialization;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.sda.medicalcrm.entity.Doctor;
//import pl.sda.medicalcrm.entity.Specialization;
//import pl.sda.medicalcrm.repository.SpecializationRepository;
//import pl.sda.medicalcrm.repository.UserRepository;
//import pl.sda.medicalcrm.service.UserService;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//@Transactional
//class SpecializationServiceTest {
//
//    @Autowired
//    private EntityManager em;
//    @Autowired
//   private SpecializationRepository specializationRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Test
//    void connectSpecializationDoctorTest(){
//
//        //given
//        var doctor = new Doctor("joanna", "Joanna1!", "1234567", "Joanna", "leśniewska");
//        var specialization = new Specialization("doctor");
//
//
//
//        specialization.addDoctor(doctor);
//
//        //when
//        em.persist(specialization);
//        em.flush();
//        em.clear();
//
//        var readSpecialization = em.find(Specialization.class, specialization.getId());
//
//        //then
//
//        assertEquals(readSpecialization,specialization);
//        assertEquals(readSpecialization.getDoctors().get(0), doctor);
//
//
//
//    }
//
//}