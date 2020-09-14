package pl.sda.medicalcrm.rest;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.medicalcrm.entity.*;
import pl.sda.medicalcrm.facade.PatientFacade;
import pl.sda.medicalcrm.service.PatientService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientFacade patientFacade;

    @PostMapping
    public @ResponseBody
    Long RegisterNewPatient(@RequestBody @Valid Patient patient) throws MailjetSocketTimeoutException, MailjetException {
        return patientFacade.registerNewPatient(patient);
    }

    @PutMapping(path = "/{userId}")
    public @ResponseBody
    Long changePatientData(@PathVariable Long userId,
                           @RequestBody @Valid Patient patient) {
        return patientService.changePatientData(userId, patient);
    }

    @GetMapping(path = "/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Patient getPatientData(@PathVariable Long userId) {
        return patientService.getPatientData(userId);
    }

    @DeleteMapping(path = "/{userId}")
    public @ResponseBody String deletePatient (@PathVariable Long userId) {
        return patientService.deletePatient(userId);
    }

    @GetMapping
    public @ResponseBody
    List<User> getAllPatientsList() {
        return patientService.getAllPatientsList();
    }

}