package com.example.appmetanit.validation;

import android.widget.EditText;
import android.widget.TextView;

public class PasswordValidator {

    public static boolean isValidPassword(EditText passField) {
        String pass = passField.getText().toString();
        try {
            assert !pass.toLowerCase().equals(pass);
            assert pass.length() >= 5;
//            assert pass.matches("[@#$]+");
        } catch (AssertionError ignored) {
            return false;
        }
        return true;
    }

    public static boolean isValidPasswordStr(CharSequence password) {
        String pass = password.toString();
        try {
            assert !pass.toLowerCase().equals(pass);
            assert pass.length() >= 5;
//            assert pass.matches("[@#$]+");
        } catch (AssertionError ignored) {
            return false;
        }
        return true;
    }
}
