package com.example.chenz.infs3634quiz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

                String userType = checkLogin(username, password);
                if (userType == null) {
                    Toast.makeText(MainActivity.this, "Could not log you in, please try again", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    if (userType.equals("student")) {
                        intent = new Intent(MainActivity.this, DashboardActivity.class);
                    } else if (userType.equals("teacher")) {
                        //change to teacher page
                        intent = new Intent(MainActivity.this, TeacherDashboardActivity.class);
                    } else {
                        Toast.makeText(MainActivity.this, "There was an error retrieving your details. Please try registering.", Toast.LENGTH_SHORT).show();
                    }
                    intent.putExtra("Username", username);
                    startActivity(intent);
                }
            }
        });

        mSignupButton = (Button) findViewById(R.id.button_register);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private String checkLogin(String username, String password) {
        SQLiteDatabase db = DBHandler.getHandler(this).getReadableDatabase();
        String query = "SELECT username, password, userType FROM LOGIN WHERE username='" + username + "'; ";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if (password.equals(cursor.getString(cursor.getColumnIndex("password")))) {
            String type = cursor.getString(cursor.getColumnIndex("userType"));
            cursor.close();
            return type;
        } else {
            cursor.close();
            return null;
        }
    }
}
