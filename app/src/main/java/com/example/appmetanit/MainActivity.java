package com.example.appmetanit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmetanit.helpers.PlaySound;
import com.example.appmetanit.validation.LoginValidator;
import com.example.appmetanit.validation.PasswordValidator;
import com.example.appmetanit.validation.ValidationLoginForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText emailField;
    EditText passwordField;
    TextView test;
    Button loginBtn;
    Button registrationBtn;
    SQLiteDatabase db;
    DBConnector DB;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.loginField);
        passwordField = findViewById(R.id.passwordField);
        loginBtn = findViewById(R.id.loginButton);
        registrationBtn = findViewById(R.id.registrationBtn);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
    }

    protected void onStart(){
        super.onStart();




//        DB = new DBConnector("root", "Qaz123wsx@");
//        test.setText(DB.SelectResult("SELECT * FROM users"));
        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!PasswordValidator.isValidPasswordStr(charSequence)) {
                    passwordField.setTextColor(Color.RED);
                } else {
                    passwordField.setTextColor(Color.GREEN);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LoginValidator.ValidationEmailInField(charSequence, emailField);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationLoginForm.isFormValid(emailField, passwordField)) {
                    if (BDCreator.IfUserInBd(db, emailField.getText().toString())) {
                        if (BDCreator.LoginUser(db, emailField.getText().toString(), passwordField.getText().toString())) {
                            Intent loggedPage = new Intent(MainActivity.this, LoggedUserPage.class);
                            loggedPage.putExtra("userName", emailField.getText().toString());
                            startActivity(loggedPage);
                            Toast.makeText(MainActivity.this, "Success login", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            PlaySound.Play_Sound(MainActivity.this, R.raw.povezlo_povezlo);
                        }
                    } else {

                        Toast.makeText(MainActivity.this, "User not register", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Login must be email\n\nPassword must be:\n" +
                            "- len more 5 symbols\n" +
                            "- contains 1 Upper case symbol", Toast.LENGTH_SHORT).show();
                }


                DB = new DBConnector("1");
                DB.start();
//                DB.returnData();





            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onPause() {
        super.onPause();
        passwordField.setText("");
        emailField.setText("");
        passwordField.findFocus();
    }

}