package pl.sda.medicalcrm.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.medicalcrm.entity.*;
import pl.sda.medicalcrm.repository.AddressRepository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void testCreateAddress() {
        //given
        var address = new Address();
        address.setStreet("Puławska 142");
        address.setCity("Warszawa");
        address.setCountry("Poland");
        address.setZipCode("00-756");

        //when
        var addressId = addressService.createAddress(address);

        //then
        var addressOptional = addressRepository.findById(addressId);
        assertTrue(addressOptional.isPresent());
        assertEquals(addressOptional.get().getId(), addressId);
//        System.out.println("ID: " + addressOptional.get().getId());
//        System.out.println("Ulica: " + addressOptional.get().getStreet());

    }


    @Test
    void testGettingListOfAllAddresses() {
        //given
        var address = new Address();
        address.setStreet("Puławska 122");
        address.setCity("Warszawa");
        address.setCountry("Poland");
        address.setZipCode("00-756");

        //when
        var addressId = addressService.createAddress(address);

        //then

        var addressesList = addressService.getListOfAllAddresses();
        assertFalse(addressesList.isEmpty());
        System.out.println(addressesList);


    }
}
