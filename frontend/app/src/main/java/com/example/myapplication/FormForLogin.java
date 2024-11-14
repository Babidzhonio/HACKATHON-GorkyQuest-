package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormForLogin extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_for_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Проверка на пустые поля
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }
        // Создаем сервис API для отправки запроса
        ApiServise apiService = RetrofitClient.getClient().create(ApiServise.class);
        LoginRequest loginRequest = new LoginRequest(email, password);
// Отправляем запрос на сервер для выполнения логина
        Call<ResponseBody> call = apiService.loginUser(loginRequest);
        // Асинхронно обрабатываем ответ с помощью LoginCallback
        call.enqueue(new LoginCallback());
    }
    // Внутренний класс для обработки ответа от сервера
    private class LoginCallback implements Callback<ResponseBody> {

/*Call<ResponseBody> call
Call — это интерфейс, предоставляемый Retrofit, который представляет собой HTTP-запрос.
<ResponseBody> — это тип данных, который ожидается в ответе от сервера.
В данном случае используется ResponseBody, который представляет необработанный ответ в виде тела HTTP.
Это позволяет получать ответ от сервера в сыром формате, который можно затем обработать
(например, как строку, JSON, XML и т. д.).
Response — это объект, который представляет HTTP-ответ от сервера.
<ResponseBody> — это тип данных, содержащий тело ответа от сервера
*/        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
                handleSuccessResponse(response);
            } else {
                handleErrorResponse(response);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e("LoginActivity", "Ошибка подключения: " + t.getMessage());
            showToast("Ошибка подключения");
        }

        // Обработка успешного ответа
        private void handleSuccessResponse(Response<ResponseBody> response) {
            try {
                String responseString = response.body().string();
                showToast("Успешный вход ");
                // Переходим на главный экран
                navigateToHome();
            } catch (IOException e) {
                // Логируем и показываем сообщение об ошибке обработки ответа
                Log.e("LoginActivity", "Ошибка обработки ответа", e);
                showToast("Ошибка обработки ответа");
            }
        }

        // Обработка ответа с ошибкой
        private void handleErrorResponse(Response<ResponseBody> response) {
            try {
                // Получаем текст ошибки, если он присутствует
                String errorResponse = response.errorBody() != null ? response.errorBody().string() : "Неизвестная ошибка";
                showToast("Ошибка входа: "+errorResponse);
            } catch (IOException e) {
                Log.e("LoginActivity", "Ошибка обработки ошибки", e);
                showToast("Ошибка обработки ответа ошибки");
            }
        }
    }

    // Метод для перехода на главную Activity
    private void navigateToHome() {
        // Создаем Intent для запуска HomeActivity
        Intent intent = new Intent(FormForLogin.this, HomeActivity.class);

        // Запускаем HomeActivity
        startActivity(intent);

        // Завершаем текущую Activity, чтобы она не осталась в стеке
        finish();
    }

    // Метод для отображения Toast-сообщений
    private void showToast(String message) {
        Toast.makeText(FormForLogin.this, message, Toast.LENGTH_SHORT).show();
    }
}