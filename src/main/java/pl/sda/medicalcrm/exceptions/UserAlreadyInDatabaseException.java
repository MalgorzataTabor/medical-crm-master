package pl.sda.medicalcrm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "User already exists in Data base")
public class UserAlreadyInDatabaseException extends RuntimeException {
}
