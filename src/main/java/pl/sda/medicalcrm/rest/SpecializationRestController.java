package pl.sda.medicalcrm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.medicalcrm.entity.Specialization;
import pl.sda.medicalcrm.service.SpecializationService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/specializations")
@CrossOrigin(origins = "http://localhost:4200")
public class SpecializationRestController {

    @Autowired
    private SpecializationService specializationService;

    @GetMapping
    public @ResponseBody
    List<Specialization> getAllSpecializationsList() {
        return specializationService.getAllSpecializationsList();
    }

    @PostMapping
    public @ResponseBody
    Long createNewSpecialization(@RequestBody @Valid Specialization specialization) {
        return specializationService.createNewSpecialization(specialization);
    }

    @PutMapping(path = "/{specializationId}")
    public @ResponseBody Long addDoctorToSpecialization (@PathVariable Long specializationId,
                                                         @RequestBody Long doctorId) {
        return specializationService.addDoctorToSpecialization(specializationId, doctorId);
    }

}




