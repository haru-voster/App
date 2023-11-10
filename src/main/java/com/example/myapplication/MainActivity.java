package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //system/app dependence and user permission @harounvoster(developer)
        // SELECTION OF MSG TEXT AND BUTTON CREATED
        TextView msg_text = findViewById(R.id.text_msg);
        Button login_btn = findViewById(R.id.login_btn);
        // creating the biometric manager and letting it check ig our user can use the fingerprint sensor or not
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            //different switch case possibility
            case BiometricManager.BIOMETRIC_SUCCESS: //biometric sensor can be used
                msg_text.setText("use fingerprint sensor to login");
                msg_text.setTextColor(color.parseColor(colorString: "#fafafa"));
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                msg_text.setText("Device has no fingerprint sensor");
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                msg_text.setText("biometric fingersensor unavailale");
                login_btn.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                msg_text.setText("your device dont have any saved fingerprint");
                login_btn.setVisibility(View.GONE);
                break;
        }// executor
        Executor executor = ContextCompat.getMainExecutor(Context:this)
        //biometric prompt call back
        //which will give us the results of the authentication
        BiometricPrompt biometricPrompt = new androidx.biometric.BiometricPrompt(MainActivity.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        })
    }


    }

