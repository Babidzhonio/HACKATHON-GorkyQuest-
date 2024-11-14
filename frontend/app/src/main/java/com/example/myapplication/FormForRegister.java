package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.activity.EdgeToEdge;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class FormForRegister extends AppCompatActivity {
    private EditText etName, etEmail, etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_for_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }) ;
    etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    public void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Проверка на пустые поля
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showToast("Все поля должны быть заполнены");
            return;
        }

        // Создание запроса регистрации
        Users registerRequest = new Users(name, email, password);
        ApiServise apiService = RetrofitClient.getClient().create(ApiServise.class);

        Call<ResponseBody> call = apiService.registerUser(registerRequest);
        call.enqueue(new RegisterCallback());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class RegisterCallback implements Callback<ResponseBody> {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
                handleSuccessResponse(response);
            } else {
                handleErrorResponse(response);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e("RegisterActivity", "API call failed: " + t.getMessage());
            showToast("Произошла ошибка при подключении к серверу");
        }

        private void handleSuccessResponse(Response<ResponseBody> response) {
            try {
                // Получаем JSON-ответ как строку и выводим его
                String jsonResponse = response.body().string();
                showToast(jsonResponse);
            } catch (IOException e) {
                Log.e("RegisterActivity", "Error reading response", e);
                showToast("Ошибка чтения ответа");
            }
        }

        private void handleErrorResponse(Response<ResponseBody> response) {
            try {
                // Обрабатываем ошибку и получаем тело ошибки
                if (response.errorBody() != null) {
                    String errorResponse = response.errorBody().string();
                    showToast(errorResponse);
                } else {
                    showToast("Неизвестная ошибка");
                }
            } catch (IOException e) {
                Log.e("RegisterActivity", "Error reading error response", e);
                showToast("Ошибка чтения ответа ошибки");
            }
        }
    }





// кнопка вернуться назад
    public void GoMainForm(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}