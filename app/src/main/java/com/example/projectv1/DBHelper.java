package com.example.projectv1;

////maha 26th of may

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "BlueHeaven11.db";

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
    public static final String COL66 ="Availability";

// Available Items TABLE
public static final String TABLE3 = "RentedItems";
    public static final String COL111 = "ItemName";
    public static final String COL222 = "UserName";



    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    public static DBHelper getInstance() {
        return null;
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1  ) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE);
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE2);
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE3);


    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String statement = "CREATE TABLE Users(username Text primary key , password TEXT, email TEXT   )";
        sqLiteDatabase.execSQL(statement);
        String statement2 = "CREATE TABLE Items(ItemName Text primary key , Contact TEXT, Price TEXT, Category TEXT, OwnerName TEXT, Availability TEXT )";
        sqLiteDatabase.execSQL(statement2);
        String statement3 = "CREATE TABLE RentedItems(ItemName Text , UserName Text, PRIMARY KEY (ItemName, UserName))" ;
        sqLiteDatabase.execSQL(statement3);

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
        Cursor cursor = MyDB.rawQuery("Select * from  Users  WHERE  LOWER(username)  = LOWER(?)", new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from  Users  where  LOWER(username)  = LOWER(?) and  password  = ?", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }

    public  Boolean checkEmail(String email ){ // modify on this
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Users  where  LOWER(email)  = LOWER(?)", new String[]{email});
        if(cursor.getCount()>0) return true;
        return false;
    }
//// METHOD FOR ITEMS STARTS HERE

    @SuppressLint("SuspiciousIndentation")
    public boolean insertitemdata(String name, String contact, String price, String category, String owner, String availability ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL11, name);
        contentValues.put(COL22, contact);
        contentValues.put(COL33 ,price);
        contentValues.put(COL44 ,category);
        contentValues.put(COL55, owner);
        contentValues.put(COL66, availability);




        long result=DB.insert(TABLE2, null, contentValues);
        if(result==-1)
            return false;
            return true;

    }

    public boolean  deleteitemdata(String name, String owner){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM Items WHERE LOWER(ItemName) = LOWER(?) AND OwnerName = ?", new String[] {name, owner});
        if(cursor.getCount()>0) {
            long result = DB.delete("Items", "LOWER(ItemName)=LOWER(?)", new String[]{name});
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
        Cursor cursor= DB.rawQuery("Select * From Items Where LOWER(ItemName) = LOWER(?)", new String[] {name});
        if(cursor.getCount()>0) {
            return false;}
        else
            return true;


    }

    //EXTRA METHODS
    public boolean isAvailable(String item)
    {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor= DB.rawQuery("Select Availability From Items Where LOWER(ItemName) = LOWER(?)", new String[] {item});
        if (cursor.moveToFirst()) {
            String availability = cursor.getString(0);
            if(availability.equals("yes"))
                return true;
            return false;
        }
        return false;
    }

    public  Cursor getAllAvailable()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor= DB.rawQuery(" SELECT * From Items WHERE LOWER(availability) = 'yes' ", null);
        return cursor;
    }

    // AvailableItems TABLE methods starts HERE


public boolean rent(String item, String user)
{
    if ( isAvailable(item))//checking for availability, already check for existence in SeekerPage
    {
        SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues= new ContentValues();
            contentValues.put(COL111, item);
            contentValues.put(COL222, user);


                //upadte availbility

                Cursor cursor= DB.rawQuery("Select * From Items Where LOWER(ItemName) = LOWER(?) AND LOWER(OwnerName)= LOWER(?) ", new String[] {item,user});
                if(cursor.getCount()>0) {
                    return false; //because user can't rent his own items
                }


                    ContentValues cv= new ContentValues();
                    cv.put("Availability", "no");
                    long result2 = DB.update("Items", cv, "LOWER(ItemName)=LOWER(?)", new String[]{item});
                    if (result2 == -1)
                        return false;
                    else{
                        long result = DB.insert(TABLE3, null,contentValues);
                        if (result == -1) { return false;}
                        return true;
                    }
            }

         else{//not available
            return false;
        }
}// end rent

    public boolean returnItem(String item , String user){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM RentedItems WHERE LOWER(ItemName) = LOWER(?) AND LOWER(UserName) = LOWER(?)", new String[] {item,user});
        if(cursor.getCount()>0) {
            long result = DB.delete("RentedItems", "LOWER(ItemName)=LOWER(?)", new String[]{item});
            if (result == -1) {
                return false;
            } else {
                ContentValues cv= new ContentValues();
                cv.put("Availability", "yes");
                long result2 = DB.update("Items", cv, "LOWER(ItemName) =LOWER(?)", new String[]{item});
                if (result2 == -1)
                    return false;
                return true;
            }
        }else{//not the user's item to return
            return false; }
    }// end return



}//end DBHelper

//bylina

