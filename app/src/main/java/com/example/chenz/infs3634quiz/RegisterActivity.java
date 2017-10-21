package com.example.chenz.infs3634quiz;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button mButtonRegister;
    EditText mEditUsername;
    EditText mEditPassword;
    boolean mTypeSelect;
    String mUserType;
    String mUsername;
    String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEditUsername = (EditText) findViewById(R.id.edit_username);
        mEditPassword = (EditText) findViewById(R.id.edit_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = mEditUsername.getText().toString();
                mPassword = mEditPassword.getText().toString();
                User user = new User();
                if (mUserType != null && mPassword.length() > 0 && mUsername.length() == 8 && mUsername.startsWith("z")) {
                    user.setUsername(mUsername);
                    user.setPassword(mPassword);
                    user.setUserType(mUserType);
                    insertUser(user);
                } else {
                    Toast.makeText(RegisterActivity.this, "Could not register you. Please make sure you've entered a valid zID and a password, and selected a user type. ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void insertUser(User user) {
        SQLiteDatabase db = DBHandler.getHandler(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("userType", user.getUserType());
        db.insert("LOGIN", null, values);
    }

    public void onRadioButtonClicked(View view) {
        mTypeSelect = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_student:
                if (mTypeSelect) {
                    mUserType = "student";
                }
                break;
            case R.id.radio_teacher:
                if (mTypeSelect) {
                    mUserType = "teacher";
                }
                break;
        }
    }
}
