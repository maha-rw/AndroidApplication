package com.example.projectv1;
//maha 26th of may

import static com.example.projectv1.LoginActivity.UserInL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
////

import java.util.Calendar;


public class HomeActivity extends AppCompatActivity {

    private DBHelper  firebaseAuth ; //lina
    // add and delete codes
    EditText name, contact, price, category;
    Button insert,  delete, view;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = DBHelper.getInstance(); //lina

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        price = findViewById(R.id.price);
        category = findViewById(R.id.category);


        insert = findViewById(R.id.btnInsert);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String priceTXT = price.getText().toString();
                String categoryTXT = category.getText().toString();
                Boolean checkNamee= DB.checkName(nameTXT);


                //EMPTY FIELD
                if (nameTXT.equals("") || contactTXT.equals("") || priceTXT.equals("") || categoryTXT.equals("")) {
                    Toast.makeText(HomeActivity.this, "ENTER ALL FIELDS", Toast.LENGTH_LONG).show();

                }

                else if(!checkNamee){
                    Toast.makeText(HomeActivity.this, "ITEM ALREADY ADDED", Toast.LENGTH_LONG).show();


                }

                else if (!(contactTXT.length() == 10)) {
                    Toast.makeText(HomeActivity.this, "PHONE FIELD IS WRONG", Toast.LENGTH_LONG).show();

                } else if (!(categoryTXT.matches("^[a-zA-Z]+$"))) {
                    Toast.makeText(HomeActivity.this, "CATEGORY FIELD IS WRONG", Toast.LENGTH_LONG).show();

                } else {

                    Boolean checkinsertdata = DB.insertitemdata(nameTXT, contactTXT, priceTXT, categoryTXT, UserInL,"yes" );


                    if (checkinsertdata == true) {
                        Toast.makeText(HomeActivity.this, "Added", Toast.LENGTH_LONG).show();
                    } else { //FAILED FROM THE ACTUAL METHOD
                        Toast.makeText(HomeActivity.this, "Adding Failed!", Toast.LENGTH_LONG).show();

                    }
                }


            }
        });

       /* update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String priceTXT = price.getText().toString();
                String categoryTXT = category.getText().toString();

                Boolean checkupdatedata = DB.updateitemdata(nameTXT, contactTXT, priceTXT, categoryTXT);

                if (checkupdatedata == true) {
                    Toast.makeText(HomeActivity.this, "updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HomeActivity.this, "fail update", Toast.LENGTH_LONG).show();

                }

            }
        });*/
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();


                Boolean checkdeletedata= DB.deleteitemdata(nameTXT, UserInL);

                if(checkdeletedata==true){
                    Toast.makeText(HomeActivity.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(HomeActivity.this, "Deleting Failed!", Toast.LENGTH_LONG).show();

                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(HomeActivity.this, ViewItem.class));
                Cursor res =DB.getdata(UserInL);
                if(res.getCount()==0){
                    Toast.makeText(HomeActivity.this, "No Entries", Toast.LENGTH_LONG).show();
                    return;

                }
                StringBuffer buffer = new StringBuffer();

                while(res.moveToNext()){
                    buffer.append("Name : " + res.getString(0)+"\n"); /////since no insert of id?? WE START FROM 1
                    buffer.append("Contact : " + res.getString(1)+"\n");
                    buffer.append("Price : " + res.getString(2)+"\n");
                    buffer.append("Category : " + res.getString(3)+"\n");
                    buffer.append("Available : " + res.getString(5)+"\n\n");


                }
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }




        });




/*
                Cursor res =DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(HomeActivity.this, "no entery", Toast.LENGTH_LONG).show();
                    return;

                }
                StringBuffer buffer = new StringBuffer();

                while(res.moveToNext()){
                    buffer.append("Name : " + res.getString(0)+"\n"); /////since no insert of id?? WE START FROM 1
                    buffer.append("contact : " + res.getString(1)+"\n");
                    buffer.append("price : " + res.getString(2)+"\n");
                    buffer.append("category : " + res.getString(3)+"\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }




        });

*/

//for oncreate()
    }
    // menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.LOGOUT){
            Intent intent = new Intent(HomeActivity.this , MainActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id==R.id.OWNERPAGE){
            Intent intent = new Intent(HomeActivity.this , HomeActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id==R.id.SEEKERPAGE){
            Intent intent = new Intent(HomeActivity.this , SeekerPage.class);
            startActivity(intent);
            return true;
        }else
        if (id==R.id.VIWEITEM) {
            Intent intent = new Intent(HomeActivity.this, ViewItem.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }}


// linaa
