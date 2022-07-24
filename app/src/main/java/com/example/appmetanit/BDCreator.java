package com.example.appmetanit;

import static android.content.Context.MODE_PRIVATE;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Locale;
import java.util.Objects;

public class BDCreator {
//    SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

    public static void AddNewUser (SQLiteDatabase db, String login, String password) {
        CreateDB(db);
        login = login.toLowerCase(Locale.ROOT);
        if (!IfUserInBd(db, login)) {
            db.execSQL("INSERT OR IGNORE INTO users VALUES ('" + login + "', '" + password +"');");
        }
    }

    private static void CreateDB (SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users (email TEXT, password TEXT);");
    }

    public static boolean IfUserInBd (SQLiteDatabase db, String login) {
        CreateDB(db);
        login = login.toLowerCase(Locale.ROOT);
        Cursor cursor = db.rawQuery("SELECT email FROM users;", null);
        while (cursor.moveToNext()) {
            if (Objects.equals(cursor.getString(0), login)) {
                return true;
            }
        }
        return false;
    }

    public static boolean LoginUser(SQLiteDatabase db, String login, String password) {
        CreateDB(db);
        login = login.toLowerCase(Locale.ROOT);
        Cursor cursor = db.rawQuery("SELECT * FROM users;", null);
        while (cursor.moveToNext()) {
            if (Objects.equals(cursor.getString(0), login) && Objects.equals(cursor.getString(1), password)) {
                return true;
            }
        }
        return false;
    }

}
