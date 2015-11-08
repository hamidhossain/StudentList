package edu.seu.studentlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DataSource {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertStudent(Student s) {
        boolean didSucceed = false;

        try {
            ContentValues values = new ContentValues();

            values.put("fullName", s.getFullName());
            values.put("email", s.getEmail());
            values.put("mobile", s.getMobile());

            didSucceed = db.insert(DbHelper.STUDENT_TABLE_NAME, null, values) > 0;
        }
        catch (Exception e) {
            //Do Nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public void deleteStudent(int id) {
        db.execSQL("DELETE FROM " + DbHelper.STUDENT_TABLE_NAME + " WHERE id = " + id);
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> arrayStudents = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + DbHelper.STUDENT_TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            Student student;

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                student = new Student();

                // set _id
                student.setId(cursor.getInt(0));
                student.setFullName(cursor.getString(1));
                student.setEmail(cursor.getString(2));
                student.setMobile(cursor.getString(3));

                arrayStudents.add(student);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            arrayStudents = new ArrayList<>();
        }

        return arrayStudents;
    }
}
