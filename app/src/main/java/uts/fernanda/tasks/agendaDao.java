package uts.fernanda.tasks;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import uts.fernanda.tasks.database.DatabaseHelper;
import uts.fernanda.tasks.model.Agenda;

public class agendaDao {
    private SQLiteDatabase database;

    public agendaDao(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long addAgenda(Agenda agenda) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, agenda.getName());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, agenda.getDescription());
        values.put(DatabaseHelper.COLUMN_STATUS, agenda.getStatus());
        return database.insert(DatabaseHelper.TABLE_AGENDA, null, values);
    }

    public List<Agenda> getAllAgendas() {
        List<Agenda> agendaList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_AGENDA, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Agenda agenda = new Agenda(
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STATUS))
                );
                agendaList.add(agenda);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return agendaList;
    }

    public void deleteAgenda(int id) {
        database.delete(DatabaseHelper.TABLE_AGENDA, DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}
