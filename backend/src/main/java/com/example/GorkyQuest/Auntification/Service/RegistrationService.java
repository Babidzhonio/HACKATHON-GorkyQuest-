package com.example.GorkyQuest.Auntification.Service;

import com.example.GorkyQuest.Exception.UserRegistrationException;
import com.example.GorkyQuest.Auntification.Model.User;
import com.example.GorkyQuest.Auntification.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+" +
            "@(gmail|yandex|outlook|mail|icloud|hotmail|aol|zoho)\\.(com|ru)$";

    @Autowired
    public RegistrationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    public String registerUser(User user) {

            if (!userRepository.existsById(1L)) {
                userRepository.save(user);
                return "Пользователь успешно добавлен";
            }

        // Проверка на существующий email
        if (userRepository.existsByEmail(user.getEmail())) {
           throw new UserRegistrationException("Пользователь с такой почтой уже существует");
        }

        if (!Pattern.matches(EMAIL_PATTERN, user.getEmail())) {
            throw new UserRegistrationException("Некорректный формат электронной почты");
        }
        // Сохранение нового пользователя
        userRepository.save(user);
        return "Пользователь успешно добавлен";
    }

}
