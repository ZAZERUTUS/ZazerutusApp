package com.example.appmetanit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmetanit.validation.LoginValidator;
import com.example.appmetanit.validation.PasswordValidator;
import com.example.appmetanit.validation.ValidationLoginForm;

public class RegistrationActivity extends AppCompatActivity {

    EditText emailField;
    EditText passwordField;
    Button regBtn;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailField = findViewById(R.id.loginField1);
        passwordField = findViewById(R.id.passwordField1);
        regBtn = findViewById(R.id.registerBtn);

    }

    protected void onStart() {
        super.onStart();

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

        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

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

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS users (email TEXT, password TEXT);");


                if (ValidationLoginForm.isFormValid(emailField, passwordField)) {
                    if (!BDCreator.IfUserInBd(db, emailField.getText().toString())) {
                        BDCreator.AddNewUser(db, emailField.getText().toString(), passwordField.getText().toString());
                        Intent loggedPage = new Intent(RegistrationActivity.this, LoggedUserPage.class);
                        loggedPage.putExtra("userName", emailField.getText().toString());
                        startActivity(loggedPage);
                    } else {
                        Toast.makeText(RegistrationActivity.this, "User already registered", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrationActivity.this, "INVALID", Toast.LENGTH_SHORT).show();
                }
                Cursor cursor = db.rawQuery("SELECT * FROM users;", null);
                TableLayout debugView = findViewById(R.id.tableForUsers);

                while (cursor.moveToNext()) {
                    String name = cursor.getString(0);
                    String password = cursor.getString(1);
                    TableRow row = new TableRow(RegistrationActivity.this);

                    TextView email = new TextView(RegistrationActivity.this);
                    email.setText(name);
                    row.addView(email, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

                    TextView passwordF = new TextView(RegistrationActivity.this);
                    passwordF.setText(password);
                    row.addView(passwordF, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

                    debugView.addView(row);

                }

            }
        });


    }
}
