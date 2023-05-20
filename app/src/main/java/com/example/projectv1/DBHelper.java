package com.example.projectv1;
////

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "EntireSystem1.db";

//USERS COLUMNS:
    public static final String TABLE1 = "Users";
    public static final String COL1 = "username";
    public static final String COL2 = "password";
    public static final String COL3 ="email";


//ITEMS COLUMNS:
    public static final String TABLE2 = "Items";
    public static final String COL11 = "itemname";
    public static final String COL22 = "contact";
    public static final String COL33 ="price";
    public static final String COL44 ="category";
    public static final String COL55 ="ownername";



    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    public static DBHelper getInstance() {
        return null;
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1  ) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE1);
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE2);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String statement = "CREATE TABLE Users(username Text primary key , password TEXT, email TEXT   )";
        String statement2 = "CREATE TABLE Items(itemname Text primary key , contact TEXT, price float(9,2), category TEXT, ownername TEXT)";


        sqLiteDatabase.execSQL(statement);
        sqLiteDatabase.execSQL(statement2);

        // sqLiteDatabase.execSQL("create Table " + TABLE + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT,  " +  COLU3 + " TEXT, " +  COLU4 + " TEXT, " +  COLU5 + " TEXT)  ");
    }

    // @Override

    public Boolean insertUser(String username, String password,String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        contentValues.put(COL3 ,email);

        long result = MyDB.insert(TABLE1, null, contentValues);
        if(result==-1)
            return false;
        return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE1 + " where " + COL1 + " = ?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE1 + " where " + COL1 + " = ? and " + COL2 + " = ?", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }

    public  Boolean checkEmail(String email ){ // modify on this
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE1 + " where " + COL3 + " = ?", new String[]{email});
        if(cursor.getCount()>0) return true;
        return false;
    }
// end USER TABLE METHODS



    public boolean insertitemdata(String name, String contact, String price, String category, String ownername){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put(COL11, name);
        cv.put(COL22, contact);
        cv.put(COL33, price);
        cv.put(COL44, category);
        cv.put(COL55, ownername); ///////////////*************

        long result=DB.insert("Items", null, cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean  deleteitemdata(String itemnamee, String ownernamee){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor= DB.rawQuery("Select * From Items Where itemname = ? AND ownername = ? ", new String[] {itemnamee, ownernamee});
        if(cursor.getCount()>0) {
            String[] whereArgs = new String[]{ itemnamee };
            int numRowsDeleted = DB.delete("Items", "itemname = ?", whereArgs);
            if (numRowsDeleted == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean  checkName(String name){
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor= DB.rawQuery("Select * From Items Where itemname = ?", new String[] {name});
        if(cursor.getCount()>0) {
            return false;}
        else
            return true;


    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor= DB.rawQuery("Select * From Items ", null);
        return cursor;
    }

}//end DBHelper

// lina
