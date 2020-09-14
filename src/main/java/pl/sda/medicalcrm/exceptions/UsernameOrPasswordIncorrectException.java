package pl.sda.medicalcrm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Username or password is incorrect")
public class UsernameOrPasswordIncorrectException extends RuntimeException {
}
