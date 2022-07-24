package com.example.appmetanit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmetanit.helpers.PlaySound;

public class LoggedUserPage extends AppCompatActivity {
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_user_page);
        PlaySound.Play_Sound(this, R.raw.iroha_senppai);

        Bundle args = getIntent().getExtras();
        userName = findViewById(R.id.userNameHeader);
        String username = args.get("userName").toString().split("@")[0];
        userName.setText("Hellow! " + username);
    }
}
