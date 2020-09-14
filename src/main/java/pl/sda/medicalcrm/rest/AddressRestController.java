package pl.sda.medicalcrm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.medicalcrm.entity.Address;
import pl.sda.medicalcrm.service.AddressService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/addresses")
public class AddressRestController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public @ResponseBody List<Address> getListOfAllAddresses() {
        return addressService.getListOfAllAddresses();
    }

    @PostMapping
    public @ResponseBody
    Long createAddress(@RequestBody @Valid Address address) {
        return addressService.createAddress(address);
    }
}


