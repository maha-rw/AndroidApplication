package com.example.projectv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        price = findViewById(R.id.price);
        category = findViewById(R.id.category);


        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DataBaseHelper(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String priceTXT = price.getText().toString();
                String categoryTXT = category.getText().toString();

                //EMPTY FIELD
                if (nameTXT.equals("") || contactTXT.equals("") || priceTXT.equals("") || categoryTXT.equals("")) {
                    Toast.makeText(HomeActivity.this, "ENTER ALL FIELDS", Toast.LENGTH_LONG).show();

                } else if (!(contactTXT.length() == 10)) {
                    Toast.makeText(HomeActivity.this, "PHONE FIELD IS WRONG", Toast.LENGTH_LONG).show();

                } else if (!(categoryTXT.matches("^[a-zA-Z]+$"))) {
                    Toast.makeText(HomeActivity.this, "CATEGORY FIELD IS WRONG", Toast.LENGTH_LONG).show();

                } else {
                    Boolean checkinsertdata = DB.insertitemdata(nameTXT, contactTXT, priceTXT, categoryTXT);

                    if (checkinsertdata == true) {
                        Toast.makeText(HomeActivity.this, "added", Toast.LENGTH_LONG).show();
                    } else { //FAILED FROM THE ACTUAL METHOD
                        Toast.makeText(HomeActivity.this, "fail add", Toast.LENGTH_LONG).show();

                    }
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
