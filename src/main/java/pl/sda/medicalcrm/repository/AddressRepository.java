package pl.sda.medicalcrm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.medicalcrm.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
