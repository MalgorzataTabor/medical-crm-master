package pl.sda.medicalcrm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.medicalcrm.entity.Specialization;


public interface SpecializationRepository extends CrudRepository <Specialization, Long> {

}
