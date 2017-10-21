package com.example.chenz.infs3634quiz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class TeacherDashboardActivity extends AppCompatActivity {

    Button mButtonUpload;
    Button mButtonDownload;
    Button mButtonResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        mButtonResults = (Button) findViewById(R.id.button_results);
        mButtonResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherDashboardActivity.this, TeacherResultActivity.class);
                startActivity(intent);
            }
        });
        mButtonUpload = (Button) findViewById(R.id.button_upload);
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upload file functionality provided by AFileChooser
                //https://github.com/iPaulPro/aFileChooser
                Intent getContent = FileUtils.createGetContentIntent();
                Intent intent = Intent.createChooser(getContent, "Select an Excel file");
                startActivityForResult(intent, 123);
            }
        });
        mButtonDownload = (Button) findViewById(R.id.button_download);
        mButtonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dropbox.com/s/rvo7uf7064pdk8u/QuizFormat.xls?dl=0"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            final Uri uri = data.getData();
            String path = FileUtils.getPath(this, uri);

            //JExcel API used to read Excel spreadsheets
            //http://jexcelapi.sourceforge.net/

            SQLiteDatabase db = DBHandler.getHandler(this).getWritableDatabase();
            File file = new File(path);
            try {
                Workbook workbook = Workbook.getWorkbook(file);
                Sheet sheet = workbook.getSheet(0);
                int length = sheet.getRows();
                Question[] questions = new Question[length];
                for (int i = 1; i < length; i++) {
                    Cell title = sheet.getCell(1, 0);
                    Cell cell;
                    questions[i] = new Question();
                    questions[i].setmQuizName(title.getContents());
                    cell = sheet.getCell(i, 1);
                    questions[i].setmQuestionText(cell.getContents());
                    cell = sheet.getCell(i, 2);
                    questions[i].setmAnswerA(cell.getContents());
                    cell = sheet.getCell(i, 3);
                    questions[i].setmAnswerB(cell.getContents());
                    cell = sheet.getCell(i, 4);
                    questions[i].setmAnswerC(cell.getContents());
                    cell = sheet.getCell(i, 5);
                    questions[i].setmAnswerD(cell.getContents());
                    cell = sheet.getCell(i, 6);
                    questions[i].setmCorrectAnswer(cell.getContents());
                    ContentValues values = new ContentValues();
                    values.put("question", questions[i].getmQuestionText());
                    values.put("answerA", questions[i].getmAnswerA());
                    values.put("answerB", questions[i].getmAnswerB());
                    values.put("answerC", questions[i].getmAnswerC());
                    values.put("answerD", questions[i].getmAnswerD());
                    values.put("correctanswer", questions[i].getmCorrectAnswer());
                    values.put("quiz", questions[i].getmQuizName());
                    db.insert("QUIZ", null, values);
                }
                Toast.makeText(this, "Uploaded successfully! " + length + "questions were uploaded. ", Toast.LENGTH_LONG);

            } catch (Exception ex) {
                Log.d("Teacher Dashboard", "onActivityResult: " + ex.toString());
            }
        }
    }
}
