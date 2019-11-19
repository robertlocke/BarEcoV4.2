package com.example.barecov1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import javax.xml.transform.Result;

//https://www.youtube.com/watch?v=NT1qxmqH1eM
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATBASE_VERSION = 1;
    private static final String DATBASE_NAME = "ecoapplication.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_PASSWORD = "PASSWORD";

    SQLiteDatabase db;


    public DatabaseHelper(Context context){
        super(context, DATBASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT,PASSWORD TEXT)");
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


    public boolean addUser(User user){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_EMAIL, user.getEmail());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());

        //http://www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


    public String[] searchPassword(String email){
        //https://stackoverflow.com/questions/8557716/how-to-return-multiple-values
        String[] arr = new String[3];
        db = this.getReadableDatabase();
        String query = "select id, email, password from "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        String dbId;
        String dbEmail, dbPassword;
        dbPassword = "Not Found";

        if(cursor.moveToFirst()){
            do{
                dbId = cursor.getString(0);
                dbEmail = cursor.getString(1);
                dbPassword = cursor.getString(2);

             if(dbEmail.equals(email)){
                 dbPassword = cursor.getString(2);

                 arr[0] = dbId;
                 arr[1] = dbEmail;
                 arr[2] = dbPassword;

                 break;
             }

            }while(cursor.moveToNext());
        }

        return arr;
    }

//https://www.youtube.com/watch?v=PA4A9IesyCg&list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07&index=5
    public boolean updateDate(String id, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});

        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
       return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }




}
