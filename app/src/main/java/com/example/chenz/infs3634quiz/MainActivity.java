package com.example.chenz.infs3634quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mLoginField;
    EditText mPasswordField;
    Button mLoginButton;
    Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginField = (EditText) findViewById(R.id.edit_login);
        mPasswordField = (EditText) findViewById(R.id.edit_password);

        mLoginButton = (Button) findViewById(R.id.button_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mLoginField.getText().toString();
                String password = mPasswordField.getText().toString();

                //placeholder for proper login database
                if (username.equals("z1234567") && password.equals("student1")) {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Could not log you in, please try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        mSignupButton = (Button) findViewById(R.id.button_register);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to signup page
            }
        });

    }
}
