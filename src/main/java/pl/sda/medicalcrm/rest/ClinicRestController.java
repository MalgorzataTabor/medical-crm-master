package pl.sda.medicalcrm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.medicalcrm.entity.Address;
import pl.sda.medicalcrm.entity.Clinic;
import pl.sda.medicalcrm.repository.AddressRepository;
import pl.sda.medicalcrm.repository.ClinicRepository;
import pl.sda.medicalcrm.service.AddressService;
import pl.sda.medicalcrm.service.ClinicService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(path = "/clinics")
public class ClinicRestController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping
    public @ResponseBody
    List<Clinic> getAllClinicsList() {
        return clinicService.getAllClinicsList();
    }

    @PostMapping
    public @ResponseBody Long createNewClinic(@RequestBody Address address){
        return clinicService.createNewClinic(address);
    }
}







