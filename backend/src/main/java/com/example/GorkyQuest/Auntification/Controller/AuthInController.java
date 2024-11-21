package com.example.GorkyQuest.Auntification.Controller;

import com.example.GorkyQuest.Exception.UserAuthorizationException;
import com.example.GorkyQuest.Auntification.Model.User;
import com.example.GorkyQuest.Auntification.Service.AuthInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authorization")
public class AuthInController {
    private final AuthInService authIn;

    @Autowired
    public AuthInController(AuthInService authIn) {
        this.authIn = authIn;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        try {
            String message = authIn.Authorization(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(message);
        } catch (UserAuthorizationException e) {
            // Обработка исключения и возвращение ответа с ошибкой
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            // Обработка других исключений, если необходимо
            return ResponseEntity.status(500).body("Произошла ошибка на сервере");
        }

    }

}
