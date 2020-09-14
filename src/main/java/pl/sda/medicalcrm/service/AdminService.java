package pl.sda.medicalcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.medicalcrm.entity.Admin;
import pl.sda.medicalcrm.entity.User;
import pl.sda.medicalcrm.exceptions.UserAlreadyInDatabaseException;
import pl.sda.medicalcrm.exceptions.UserNotFoundException;
import pl.sda.medicalcrm.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private boolean isLoginAlreadyInDataBase(User user) {
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
    }

    @Transactional
    public Long registerNewAdmin (Admin admin) {
        if (isLoginAlreadyInDataBase(admin)) throw new UserAlreadyInDatabaseException();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole("ADMIN");
        userRepository.save(admin);
        return admin.getId();
    }

    @Transactional
    public Long changeAdminData(Long id, Admin admin){
        if (!userRepository.findById(id).isPresent()) throw new UserNotFoundException();
        admin.setId(id);
        userRepository.save(admin);
        return admin.getId();
    }

    public String deleteAdmin(Long userId){
        userRepository.deleteById(userId);
        return "Admin Deleted";
    }

    public List<User> getAllAdminsList() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(u -> u.getRole().equals("ADMIN")).collect(Collectors.toList());
    }
}
