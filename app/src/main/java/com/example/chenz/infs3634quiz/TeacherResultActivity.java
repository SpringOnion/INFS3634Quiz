package com.example.chenz.infs3634quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class TeacherResultActivity extends AppCompatActivity {

    ListView mResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_result);

        mResultList = (ListView) findViewById(R.id.result_list);
    }
}
