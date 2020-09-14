package pl.sda.medicalcrm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.medicalcrm.dto.AuthorizationDto;
import pl.sda.medicalcrm.entity.User;
import pl.sda.medicalcrm.exceptions.UsernameOrPasswordIncorrectException;
import pl.sda.medicalcrm.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails authenticateUser(String authorization) throws UsernameNotFoundException {
        Gson gson = new GsonBuilder().create();
        AuthorizationDto authorizationDto = gson.fromJson(authorization, AuthorizationDto.class);
        UserDetails user = userRepository.findByUsername(authorizationDto.getUsername()).get();
        if (!passwordEncoder.matches(authorizationDto.getPassword(), user.getPassword()))
            throw new UsernameOrPasswordIncorrectException();
        return userRepository.findByUsername(authorizationDto.getUsername()).get();
    }
}
