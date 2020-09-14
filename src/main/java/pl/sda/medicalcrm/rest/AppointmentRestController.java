package pl.sda.medicalcrm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.medicalcrm.dto.AppointmentDto;
import pl.sda.medicalcrm.entity.Appointment;
import pl.sda.medicalcrm.entity.Examination;
import pl.sda.medicalcrm.service.AppointmentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Long makeNewAppointment(@RequestBody AppointmentDto appointmentDto){
        return appointmentService.makeNewAppointment(appointmentDto);
    }

    @DeleteMapping(path = "/{appointmentId}")
    public @ResponseBody String deleteAppointment(@PathVariable Long appointmentId) {
        return appointmentService.deleteAppointment(appointmentId);
    }

    @PutMapping(path = "/{appointmentId}")
    public @ResponseBody String addExaminationResults(@PathVariable Long appointmentId,
                                                      @RequestBody Examination examination) {
        return appointmentService.addExaminationResults(examination, appointmentId);
    }

}
