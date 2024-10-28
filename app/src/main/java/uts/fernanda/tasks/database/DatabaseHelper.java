package uts.fernanda.tasks.database;
// DatabaseHelper.java

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AgendaDB";
    private static final int DATABASE_VERSION = 1;

    // Agenda table name
    public static final String TABLE_AGENDA = "agenda";

    // Agenda Table Columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STATUS = "status";

    // Create Table SQL Query
    private static final String CREATE_TABLE_AGENDA =
            "CREATE TABLE " + TABLE_AGENDA + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_STATUS + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AGENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
        onCreate(db);
    }

    public boolean addAgenda(String name, String description, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_STATUS, status);

        long result = db.insert(TABLE_AGENDA, null, values);
        db.close();
        return result != -1;
    }
}