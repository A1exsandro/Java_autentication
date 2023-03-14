package com.nst.javaauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nst.javaauthentication.databinding.ActivityForgetBinding;

public class ForgetActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivityForgetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.btnRetrieve.setOnClickListener(v -> validation());
    }

    private void validation() {
        String email = binding.editEmail.getText().toString().trim();

        if (!email.isEmpty()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            retrieveFirebase(email);
        }else{
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_SHORT).show();
        }

    }

    private void retrieveFirebase(String email) {
        mAuth.sendPasswordResetEmail(
                email
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Já pode verificar seu e-mail", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }
}