package pl.sda.medicalcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.medicalcrm.entity.*;
import pl.sda.medicalcrm.enums.TypeOfUser;
import pl.sda.medicalcrm.exceptions.UserAlreadyInDatabaseException;
import pl.sda.medicalcrm.exceptions.UserNotFoundException;
import pl.sda.medicalcrm.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Long registerNewPatient(Patient patient) {
        if (isLoginAlreadyInDataBase(patient)) throw new UserAlreadyInDatabaseException();
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole("PATIENT");
        userRepository.save(patient);
        return patient.getId();
    }

    @Transactional
    public Long changePatientData(Long id, Patient patient) {
        if (!userRepository.findById(id).isPresent()) throw new UserNotFoundException();
        patient.setId(id);
        userRepository.save(patient);
        return patient.getId();
    }

    @Transactional
    public String deletePatient(Long patientId){
        userRepository.deleteById(patientId);
        return "Patient Deleted";
    }

    public Patient getPatientData(Long id) {
        Optional<User> optionalPatient = userRepository.findById(id);
        if (!optionalPatient.isPresent()) throw new UserNotFoundException();
        return (Patient) optionalPatient.get();
    }

    public List<User> getAllPatientsList() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(u -> u.getRole().equals("PATIENT")).collect(Collectors.toList());
    }

    private boolean isLoginAlreadyInDataBase(User user) {
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
    }
}
