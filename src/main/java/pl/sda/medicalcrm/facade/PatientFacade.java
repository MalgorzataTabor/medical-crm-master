package pl.sda.medicalcrm.facade;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.medicalcrm.entity.Doctor;
import pl.sda.medicalcrm.entity.Patient;
import pl.sda.medicalcrm.service.EmailService;
import pl.sda.medicalcrm.service.PatientService;

import javax.transaction.Transactional;
import javax.validation.Valid;


@Service
public class PatientFacade {

    @Autowired
    private PatientService patientService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Long registerNewPatient(Patient patient) throws MailjetException, MailjetSocketTimeoutException {
        Long response = patientService.registerNewPatient(patient);
        if (response != 0) emailService.sendEmail(patient);
        return response;
    }


}



