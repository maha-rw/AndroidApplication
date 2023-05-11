package com.example.projectv1;

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

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private DBHelper  firebaseAuth ; //lina

    EditText name, contact, price, category;
    Button insert, update, delete, view;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = DBHelper.getInstance(); //lina
        name= findViewById(R.id.name);
        contact= findViewById(R.id.contact);
        price= findViewById(R.id.price);
        category= findViewById(R.id.category);


        insert=findViewById(R.id.btnInsert);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        view=findViewById(R.id.btnView);

        DB= new DataBaseHelper(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();
                String contactTXT= contact.getText().toString();
                String priceTXT= price.getText().toString();
                String categoryTXT= category.getText().toString();

                Boolean checkinsertdata= DB.insertitemdata(nameTXT,contactTXT, priceTXT, categoryTXT);

                if(checkinsertdata==true){
                    Toast.makeText(HomeActivity.this, "added", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(HomeActivity.this, "fail add", Toast.LENGTH_LONG).show();

                }

            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();
                String contactTXT= contact.getText().toString();
                String priceTXT= price.getText().toString();
                String categoryTXT= category.getText().toString();

                Boolean checkupdatedata= DB.updateitemdata(nameTXT,contactTXT, priceTXT, categoryTXT);

                if(checkupdatedata==true){
                    Toast.makeText(HomeActivity.this, "updated", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(HomeActivity.this, "fail update", Toast.LENGTH_LONG).show();

                }

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();


                Boolean checkdeletedata= DB.deleteitemdata(nameTXT);

                if(checkdeletedata==true){
                    Toast.makeText(HomeActivity.this, "delete", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(HomeActivity.this, "fail delete", Toast.LENGTH_LONG).show();

                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    buffer.append("category : " + res.getString(3)+"\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }

// lina
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logoutMenu: {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(new Intent(HomeActivity.this , MainActivity.class));

            }


        }
        return super.onOptionsItemSelected(item);
        }
    }
