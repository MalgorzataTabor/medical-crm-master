package pl.sda.medicalcrm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.medicalcrm.dto.AppointmentDto;
import pl.sda.medicalcrm.entity.Appointment;
import pl.sda.medicalcrm.entity.Examination;
import pl.sda.medicalcrm.repository.AppointmentRepository;
import pl.sda.medicalcrm.repository.ClinicRepository;
import pl.sda.medicalcrm.repository.SpecializationRepository;
import pl.sda.medicalcrm.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    private boolean isAppointmentDateForUserAvailable(Appointment appointment){
        List<Appointment> allAppointments = (List<Appointment>) appointmentRepository.findAll();
        return allAppointments.stream().filter(p -> p.getUser().getId().equals(appointment.getUser().getId()))
                .anyMatch(q -> q.getAppointmentDate().equals(appointment.getAppointmentDate()));
    }

    @Transactional
    public Long makeNewAppointment(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        appointment.setUser(userRepository.findById(appointmentDto.getUserId()).get());
        appointment.setSpecialization(specializationRepository.findById(appointmentDto.getSpecializationId()).get());
        appointment.setClinic(clinicRepository.findById(appointmentDto.getClinicId()).get());
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setOnline(appointmentDto.isOnline());
        if (isAppointmentDateForUserAvailable(appointment)) return 0L;
        appointmentRepository.save(appointment);
        return appointment.getId();
    }

    public String deleteAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        appointment.setUser(null);
        appointment.setSpecialization(null);
        appointment.setClinic(null);
        appointmentRepository.deleteById(appointmentId);
        return "Appointment Deleted";
    }

    public String addExaminationResults(Examination examination, Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        appointment.getExaminations().add(examination);
        appointment.setId(appointmentId);
        appointmentRepository.save(appointment);
        return "Examination Added";
    }

}

