package edu.seu.studentlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_student);

        Button btnSaveRecord = (Button) findViewById(R.id.btnSaveRecord);
        btnSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fullNameEditText = (EditText) findViewById(R.id.txtInsertFullName);
                EditText emailEditText  = (EditText) findViewById(R.id.txtInsertEmail);
                EditText mobileEditText = (EditText) findViewById(R.id.txtInsertMobile);

                Student newStudent = new Student();
                newStudent.setFullName(fullNameEditText.getText().toString());
                newStudent.setEmail(emailEditText.getText().toString());
                newStudent.setMobile(mobileEditText.getText().toString());

                DataSource ds = new DataSource(InsertStudentActivity.this);
                ds.open();
                ds.insertStudent(newStudent);
                ds.close();

                Intent intent = new Intent(InsertStudentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
