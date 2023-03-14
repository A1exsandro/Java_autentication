package com.nst.javaauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nst.javaauthentication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textSignUp.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });

        binding.textForget.setOnClickListener(v ->
                startActivity(new Intent(this, ForgetActivity.class)));

        binding.btnLogin.setOnClickListener(v -> validation());
    }

    private void validation() {
        String email = binding.editEmail.getText().toString().trim();
        String password = binding.editPassword.getText().toString().trim();

        if (!email.isEmpty()) {
            if (!password.isEmpty()) {
                binding.progressBar.setVisibility(View.VISIBLE);
                loginFirebase(email, password);
            }else{
                Toast.makeText(this, "Informe seu password", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginFirebase(String email, String password) {
        mAuth.signInWithEmailAndPassword(
                email, password
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                finish();
                startActivity(new Intent(this, MainActivity.class));
            }else{
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}