package com.example.appmetanit.validation;

import android.graphics.Color;
import android.widget.EditText;

public class LoginValidator {
    private static String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+[.][a-zA-Z0-9.-]+$";

    public static boolean isValidEmail(EditText emailField) {
        String email = emailField.getText().toString();
        try {
            assert email.matches(regex);
        } catch (AssertionError ignored) {
            return false;
        }
        return true;
    }

    public static boolean isValidEmailString(CharSequence email) {
        try {
            assert email.toString().matches(regex);
        } catch (AssertionError ignored) {
            return false;
        }
        return true;
    }

    public static void ValidationEmailInField(CharSequence charSequence, EditText emailField){
        if (charSequence.toString().contains(" ")) {
            String correctEmail = charSequence.toString().replaceAll(" ", "");
            emailField.setText(correctEmail);
            emailField.setSelection(correctEmail.length());
            charSequence = correctEmail;
        }
        if (charSequence.toString().contains("\n")) {
            String correctEmail = charSequence.toString().replaceAll("\n", "");
            emailField.setText(correctEmail);
            emailField.setSelection(correctEmail.length());
            charSequence = correctEmail;
        }
        if (!LoginValidator.isValidEmailString(charSequence)) {
            emailField.setTextColor(Color.RED);
        } else {
            emailField.setTextColor(Color.GREEN);
        }
    }
}
