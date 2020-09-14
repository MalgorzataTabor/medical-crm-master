package pl.sda.medicalcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.medicalcrm.entity.Doctor;
import pl.sda.medicalcrm.entity.User;
import pl.sda.medicalcrm.exceptions.UserAlreadyInDatabaseException;
import pl.sda.medicalcrm.exceptions.UserNotFoundException;
import pl.sda.medicalcrm.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private boolean isLoginAlreadyInDataBase(User user) {
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
    }

    @Transactional
    public Long registerNewDoctor(Doctor doctor) {
        if (isLoginAlreadyInDataBase(doctor)) throw new UserAlreadyInDatabaseException();
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctor.setRole("DOCTOR");
        userRepository.save(doctor);
        return doctor.getId();
    }

    @Transactional
    public Long changeDoctorData(Long id, Doctor doctor) {
        if (!userRepository.findById(id).isPresent()) throw new UserNotFoundException();
        doctor.setId(id);
        userRepository.save(doctor);
        return doctor.getId();
    }

    @Transactional
    public String deleteDoctor(Long doctorId){
        userRepository.deleteById(doctorId);
        return "Doctor Deleted";
    }

    public Doctor getDoctorData(Long id) {
        Optional<User> optionalDoctor = userRepository.findById(id);
        if (!optionalDoctor.isPresent()) throw new UserNotFoundException();
        return (Doctor) optionalDoctor.get();
    }

    public List<User> getAllDoctorsList() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(u -> u.getRole().equals("DOCTOR")).collect(Collectors.toList());
    }
}
