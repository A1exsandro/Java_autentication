package com.nst.javaauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nst.javaauthentication.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.btnSignUp.setOnClickListener(v -> validation());
    }

    private void validation() {
        String email = binding.editEmail.getText().toString().trim();
        String password = binding.editPassword.getText().toString().trim();
        
        if (!email.isEmpty()) {
            if (!password.isEmpty()) {
                binding.progressBar.setVisibility(View.VISIBLE);
                singUpFirebase(email, password);
            }else{
                Toast.makeText(this, "Informe seu password", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_SHORT).show();
        }

    }

    private void singUpFirebase(String email, String password) {
        mAuth.createUserWithEmailAndPassword(
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