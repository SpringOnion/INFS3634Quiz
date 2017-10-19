package com.example.chenz.infs3634quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {

    Button mButtonRegister;
    EditText mEditUsername;
    EditText mEditPassword;
    boolean mTypeSelect;
    String mUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
