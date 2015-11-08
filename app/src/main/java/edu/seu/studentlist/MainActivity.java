package edu.seu.studentlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddNew = (Button) findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DataSource ds = new DataSource(this);
        ds.open();
        final ArrayList<Student> students = ds.getAllStudents();
        ds.close();

        if (students.size() > 0) {
            adapter = new StudentAdapter(this, students);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Student item = (Student) parent.getItemAtPosition(position);

                    Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                    intent.putExtra("studentId", item.getId());
                    intent.putExtra("studentFullName", item.getFullName());
                    intent.putExtra("studentEmail", item.getEmail());
                    intent.putExtra("studentMobile", item.getMobile());
                    startActivity(intent);
                }
            });
        }
    }
}
