package com.example.GorkyQuest.Auntification.Controller;

import com.example.GorkyQuest.Exception.UserRegistrationException;
import com.example.GorkyQuest.Auntification.Model.User;
import com.example.GorkyQuest.Auntification.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            String message = registrationService.registerUser(user);
            return ResponseEntity.ok(message);
        } catch (UserRegistrationException e){
          return ResponseEntity.status(401).body(e.getMessage());
        } catch (DataIntegrityViolationException ex){
            return ResponseEntity.status(500).body("Ошибка на сервере");
        }
    }
}

