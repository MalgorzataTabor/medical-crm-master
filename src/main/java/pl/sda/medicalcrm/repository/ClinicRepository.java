package pl.sda.medicalcrm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.medicalcrm.entity.Clinic;

public interface ClinicRepository extends CrudRepository<Clinic, Long> {
}
