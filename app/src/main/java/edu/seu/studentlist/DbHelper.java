package edu.seu.studentlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentlist.db";
    private static final int DATABASE_VERSION = 1;
    public static final String STUDENT_TABLE_NAME = "student";

    private static final String CREATE_TABLE_STUDENT =
            "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE_NAME + " ( "
                    + "id    INTEGER PRIMARY KEY AUTOINCREMENT, "  //0
                    + "fullName TEXT, "                            //1
                    + "email TEXT, "                               //2
                    + "mobile TEXT"                                //3
                    + ");";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
        onCreate(db);
    }
}
