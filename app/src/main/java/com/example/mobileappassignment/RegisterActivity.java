package com.example.mobileappassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    // Declare UI components
    private ImageButton btnBack;
    private TextInputEditText etFullName, etEmail, etUsername, etPassword, etConfirmPassword;
    private TextInputLayout tilFullName, tilEmail, tilUsername, tilPassword, tilConfirmPassword;
    private CheckBox cbTerms;
    private MaterialButton btnRegister, btnGoogle, btnFacebook;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Fix: Use the correct root layout ID
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        initViews();

        // Set up event listeners
        setupEventListeners();
    }

    private void initViews() {
        // Initialize all UI components
        btnBack = findViewById(R.id.btnBack);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        tilFullName = findViewById(R.id.tilFullName);
        tilEmail = findViewById(R.id.tilEmail);
        tilUsername = findViewById(R.id.tilUsername);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);

        cbTerms = findViewById(R.id.cbTerms);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnFacebook = findViewById(R.id.btnFacebook);
        tvLoginLink = findViewById(R.id.tvLoginLink);
    }

    private void setupEventListeners() {
        // Back button
        btnBack.setOnClickListener(v -> {
            finish(); // Close current activity and go back
        });

        // Register button
        btnRegister.setOnClickListener(v -> {
            handleRegister();
        });

        // Social login buttons
        btnGoogle.setOnClickListener(v -> {
            Toast.makeText(this, "Google login clicked", Toast.LENGTH_SHORT).show();
            // TODO: Implement Google login
        });

        btnFacebook.setOnClickListener(v -> {
            Toast.makeText(this, "Facebook login clicked", Toast.LENGTH_SHORT).show();
            // TODO: Implement Facebook login
        });

        // Login link
        tvLoginLink.setOnClickListener(v -> {
            // Go back to MainActivity (or LoginActivity if you have one)
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void handleRegister() {
        // Clear previous errors
        clearErrors();

        // Get input values
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Validate inputs
        if (!validateInputs(fullName, email, username, password, confirmPassword)) {
            return;
        }

        // Check terms and conditions
        if (!cbTerms.isChecked()) {
            Toast.makeText(this, "Vui lòng đồng ý với điều khoản và chính sách", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Implement actual registration logic
        // For now, just show success message
        Toast.makeText(this, "Đăng ký thành công! Họ tên: " + fullName, Toast.LENGTH_LONG).show();

        // Optionally, go back to main activity
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateInputs(String fullName, String email, String username, String password, String confirmPassword) {
        boolean isValid = true;

        // Validate full name
        if (fullName.isEmpty()) {
            tilFullName.setError("Vui lòng nhập họ và tên");
            isValid = false;
        }

        // Validate email
        if (email.isEmpty()) {
            tilEmail.setError("Vui lòng nhập email");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Email không hợp lệ");
            isValid = false;
        }

        // Validate username
        if (username.isEmpty()) {
            tilUsername.setError("Vui lòng nhập tên đăng nhập");
            isValid = false;
        } else if (username.length() < 3) {
            tilUsername.setError("Tên đăng nhập phải có ít nhất 3 ký tự");
            isValid = false;
        }

        // Validate password
        if (password.isEmpty()) {
            tilPassword.setError("Vui lòng nhập mật khẩu");
            isValid = false;
        } else if (password.length() < 6) {
            tilPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            isValid = false;
        }

        // Validate confirm password
        if (confirmPassword.isEmpty()) {
            tilConfirmPassword.setError("Vui lòng xác nhận mật khẩu");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            tilConfirmPassword.setError("Mật khẩu xác nhận không khớp");
            isValid = false;
        }

        return isValid;
    }

    private void clearErrors() {
        tilFullName.setError(null);
        tilEmail.setError(null);
        tilUsername.setError(null);
        tilPassword.setError(null);
        tilConfirmPassword.setError(null);
    }
}