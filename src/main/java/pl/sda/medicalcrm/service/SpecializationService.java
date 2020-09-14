package pl.sda.medicalcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.medicalcrm.entity.Doctor;
import pl.sda.medicalcrm.entity.Specialization;
import pl.sda.medicalcrm.repository.SpecializationRepository;
import pl.sda.medicalcrm.repository.UserRepository;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Specialization> getAllSpecializationsList() {
        return (List<Specialization>) specializationRepository.findAll();
    }

    @Transactional
    public Long createNewSpecialization(Specialization specialization) {
        if (isSpecializationInDataBase(specialization)) return 0L;
        specializationRepository.save(specialization);
        return specialization.getId();
    }

    @Transactional
    public Long addDoctorToSpecialization(Long specializationId, Long doctorId) {
        Specialization specialization = specializationRepository.findById(specializationId).get();
        Doctor doctor = (Doctor) userRepository.findById(doctorId).get();
        specialization.getDoctors().add(doctor);
        specialization.setId(specializationId);
        specializationRepository.save(specialization);
        return specialization.getId();
    }

    private boolean isSpecializationInDataBase(Specialization specialization) {
        List<Specialization> specializations = (List<Specialization>) specializationRepository.findAll();
        return specializations.stream()
                .anyMatch(s -> s.getTypeOfSpecialization().equals(specialization.getTypeOfSpecialization()));
    }
}
