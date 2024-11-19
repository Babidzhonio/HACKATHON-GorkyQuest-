package com.example.GorkyQuest.Repository;
import com.example.GorkyQuest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    boolean existsById(Long id);  // Проверка существования пользователя по ID
}
