package edu.seu.studentlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        final int id = bundle.getInt("studentId");

        Button btnDelete = (Button) findViewById(R.id.btnDeleteStudent);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource ds = new DataSource(StudentDetailsActivity.this);
                ds.open();
                ds.deleteStudent(id);
                ds.close();

                Intent intent1 = new Intent(StudentDetailsActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });


        TextView txtId = (TextView) findViewById(R.id.txtDetailsId);
        TextView txtFullName = (TextView) findViewById(R.id.txtDetailsFullName);
        TextView txtEmail = (TextView) findViewById(R.id.txtDetailsEmail);
        TextView txtMobile = (TextView) findViewById(R.id.txtDetailsMobile);

        txtId.setText(String.valueOf(id));
        txtFullName.setText(bundle.getString("studentFullName"));
        txtEmail.setText(bundle.getString("studentEmail"));
        txtMobile.setText(bundle.getString("studentMobile"));
    }
}
