package com.example.projectv1;
////
//maha

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueHeaven4.db";

//USERS TABLE
    public static final String TABLE = "Users";

    public static final String COL1 = "username";
    public static final String COL2 = "password";
    public static final String COL3 ="email";

    //ITEMS TABLE
    public static final String TABLE2 = "Items";

    public static final String COL11 = "ItemName";
    public static final String COL22 = "Contact";
    public static final String COL33 = "Price";
    public static final String COL44 ="Category";
    public static final String COL55 ="OwnerName";






    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    public static DBHelper getInstance() {
        return null;
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1  ) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE);
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE2);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String statement = "CREATE TABLE Users(username Text primary key , password TEXT, email TEXT   )";
        sqLiteDatabase.execSQL(statement);
        String statement2 = "CREATE TABLE Items(ItemName Text primary key , Contact TEXT, Price TEXT, Category TEXT, OwnerName TEXT )";
        sqLiteDatabase.execSQL(statement2);

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
//// METHOD FOR ITEMS STARTS HERE

    public boolean insertitemdata(String name, String contact, String price, String category, String owner ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL11, name);
        contentValues.put(COL22, contact);
        contentValues.put(COL33 ,price);
        contentValues.put(COL44 ,category);
        contentValues.put(COL55, owner);


        long result=DB.insert(TABLE2, null, contentValues);
        if(result==-1)
            return false;
        return true;
    }

    /*public boolean updateitemdata(String name, String contact, String price, String category){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        //cv.put("name", name); //SINCE WE WILL ONLY UPDATE OTHER
        cv.put("contact", contact);
        cv.put("price", price);
        cv.put("category", category);
        Cursor cursor= DB.rawQuery("Select * From ItemDetails Where name = ?", new String[] {name});
        if(cursor.getCount()>0) {
            long result = DB.update("ItemDetails", cv, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }*/

    public boolean  deleteitemdata(String name, String owner){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM Items WHERE ItemName = ? AND OwnerName = ?", new String[] {name, owner});
        if(cursor.getCount()>0) {
            long result = DB.delete("Items", "ItemName=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getALLdata(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor= DB.rawQuery("Select * From Items ", null);
        return cursor;
    }

    public Cursor getdata(String UserInL){
        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE2 + " WHERE OwnerName = '" + UserInL + "'"; // error in user
        Cursor res = DB.rawQuery(query, null);
        return res;

    }

    public boolean  checkName(String name){
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor= DB.rawQuery("Select * From Items Where ItemName = ?", new String[] {name});
        if(cursor.getCount()>0) {
            return false;}
        else
            return true;


    }



}//end DBHelper

//bylina

