package pl.sda.medicalcrm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.medicalcrm.entity.Doctor;
import pl.sda.medicalcrm.entity.Patient;
import pl.sda.medicalcrm.entity.User;
import pl.sda.medicalcrm.service.DoctorService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorRestController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public @ResponseBody
    Long registerNewDoctor(@RequestBody @Valid Doctor doctor) {
        return doctorService.registerNewDoctor(doctor);
    }

    @GetMapping
    public @ResponseBody
    List<User> getAllDoctorsList(){
        return doctorService.getAllDoctorsList();
    }

    @PutMapping(path = "/{userId}")
    public @ResponseBody Long changeDoctorData(@PathVariable Long userId,
                                                      @RequestBody @Valid Doctor doctor) {
        return doctorService.changeDoctorData(userId, doctor);
    }

    @GetMapping(path = "/{userId}")
    public @ResponseBody
    Doctor getDoctorData(@PathVariable Long userId) {
        return doctorService.getDoctorData(userId);
    }

    @DeleteMapping(path = "/{userId}")
    public @ResponseBody String deleteDoctor (@PathVariable Long userId) {
        return doctorService.deleteDoctor(userId);
    }


}
