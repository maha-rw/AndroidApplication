package com.example.projectv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login1.db";

    // public static final String TABLENAME1 = "users2";
    public static final String TABLE = "Users";
    public static final String COL1 = "username";
    public static final String COL2 = "password";

    public static final String COL3 ="email";

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1  ) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String statement = "CREATE TABLE Users(username Text primary key , password TEXT, email TEXT   )";
        sqLiteDatabase.execSQL(statement);
        // sqLiteDatabase.execSQL("create Table " + TABLE + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT,  " +  COLU3 + " TEXT, " +  COLU4 + " TEXT, " +  COLU5 + " TEXT)  ");
    }

    // @Override

    public Boolean insertData(String username, String password,String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        contentValues.put(COL3 ,email);

        long result = MyDB.insert(TABLE, null, contentValues);
        if(result==-1)
            return false;
        return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE + " where " + COL1 + " = ?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE + " where " + COL1 + " = ? and " + COL2 + " = ?", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }

    public  Boolean checkEmail(String email ){ // modify on this
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE + " where " + COL3 + " = ?", new String[]{email});
        if(cursor.getCount()>0) return true;
        return false;
    }



    /*  public DBHelper(@Nullable Context context) {
        super(context,  DBNAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table " + TABLENAME + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT" + COL3 +"TEXT)"); }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    public Boolean insertData(String username, String password , String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        contentValues.put(COL3, email);
        long result = MyDB.insert(TABLENAME, null, contentValues);
        if(result==-1) return false;
        return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ?", new String[]{username});
        if (cursor.getCount() > 0) return true; // username exists so don't accept
        return false; //available username
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ? and " + COL2 + " = ?", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }

   /* public Boolean checkemail (String email, String username){ // modify on this
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ? and " + COL3 + " = ?", new String[] {username,email});

        if(cursor.getCount()>0) return true;
        return false;
    }*/






}//end DBHelper