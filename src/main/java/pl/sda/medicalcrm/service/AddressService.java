package pl.sda.medicalcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.medicalcrm.entity.Address;
import pl.sda.medicalcrm.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressService() {
    }

    public List<Address> getListOfAllAddresses() {
        return (List<Address>) addressRepository.findAll();
    }


    public Long createAddress(Address address) {
        if (doesAddressExists(address)) return 0L;
        addressRepository.save(address);
        return address.getId();
    }

    public boolean doesAddressExists(Address address) {
        return getListOfAllAddresses().stream()
                .anyMatch(s -> s.getCity().equals(address.getCity())
                        && s.getCountry().equals(address.getCountry())
                        && s.getStreet().equals((address.getStreet()))
                        && s.getZipCode().equals(address.getZipCode())
                );
    }

}
