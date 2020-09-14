package pl.sda.medicalcrm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.medicalcrm.entity.Appointment;

public interface AppointmentRepository extends CrudRepository <Appointment, Long> {

}