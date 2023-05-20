/*About Delete feature: To delete an item, the Item Name (at least) must be entered, then
the Delete button can be pressed to perform the deletion.
 Please note that the Item Name is unique so you can’t insert 2 items with the same name.

//m

package com.example.projectv1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context )  {
        super(context, "item.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table ItemDetails(name TEXT primary key , contact TEXT, price TEXT, category TEXT )"); // add user name
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists ItemDetails");
    }

//bylina

    public boolean insertitemdata(String name, String contact, String price, String category){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("name", name);
        cv.put("contact", contact);
        cv.put("price", price);
        cv.put("category", category);

        long result=DB.insert("ItemDetails", null, cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public boolean updateitemdata(String name, String contact, String price, String category){
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
    }




    public boolean  deleteitemdata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor= DB.rawQuery("Select * From ItemDetails Where name = ?", new String[] {name});
        if(cursor.getCount()>0) {
            long result = DB.delete("ItemDetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }


    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor= DB.rawQuery("Select * From ItemDetails ", null);
        return cursor;
    }


    public boolean  checkName(String name){
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor= DB.rawQuery("Select * From ItemDetails Where name = ?", new String[] {name});
        if(cursor.getCount()>0) {
            return false;}
        else
            return true;


    }


}

*/
// lina
