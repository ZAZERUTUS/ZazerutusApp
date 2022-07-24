package com.example.appmetanit.validation;

import android.widget.EditText;

public class ValidationLoginForm {
    public static boolean isFormValid(EditText loginField, EditText passwordField) {
        try {
            assert LoginValidator.isValidEmail(loginField);
            assert PasswordValidator.isValidPassword(passwordField);
        } catch (AssertionError ignored ){
            return false;
        }
        return true;
    }
}
