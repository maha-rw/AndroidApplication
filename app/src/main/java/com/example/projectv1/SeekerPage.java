package com.example.projectv1;

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

////maha 26th of may
public class SeekerPage extends AppCompatActivity {
    private DBHelper  firebaseAuth ;

    EditText name2;
    Button rent , returnItem;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_page);
        firebaseAuth = DBHelper.getInstance(); //lina


        name2 = findViewById(R.id.name2);
        rent = findViewById(R.id.btnRent);
        returnItem = findViewById(R.id.btnReturn);

        DB = new DBHelper(this);


        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemNameTXT = name2.getText().toString();
                String userNameTXT = UserInL;

                Boolean checkItem= DB.checkName(itemNameTXT); //check item exists

                //EMPTY FIELD
                if (itemNameTXT.equals("") ) {
                    Toast.makeText(SeekerPage.this, "ENTER NAME OF ITEM", Toast.LENGTH_LONG).show();
                }
                else if(checkItem){
                    Toast.makeText(SeekerPage.this, "ITEM NOT FOUND", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkrenting = DB.rent(itemNameTXT, userNameTXT );

                    if (checkrenting == true) {
                        Toast.makeText(SeekerPage.this, "RENTED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    } else { //FAILED FROM THE ACTUAL METHOD
                        Toast.makeText(SeekerPage.this, "RENTING FAILED", Toast.LENGTH_LONG).show();

                    }
                }


            }
        });

        returnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemNameTXT = name2.getText().toString();
                String userNameTXT = UserInL;

                Boolean checkItem= DB.checkName(itemNameTXT); //check item exists

                if (itemNameTXT.equals("") ) {
                    Toast.makeText(SeekerPage.this, "ENTER NAME OF ITEM", Toast.LENGTH_LONG).show();
                }
                else if(checkItem){
                    Toast.makeText(SeekerPage.this, "ITEM NOT FOUND", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkrenting = DB.returnItem(itemNameTXT, userNameTXT );

                    if (checkrenting == true) {
                        Toast.makeText(SeekerPage.this, "Returned SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    } else { //FAILED FROM THE ACTUAL METHOD
                        Toast.makeText(SeekerPage.this, "Returning FAILED", Toast.LENGTH_LONG).show();

                    }
                }
        }
        });//end return




    }// end on create


    // menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.LOGOUT) {
            Intent intent = new Intent(SeekerPage.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.OWNERPAGE) {
            Intent intent = new Intent(SeekerPage.this, HomeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.SEEKERPAGE) {
            Intent intent = new Intent(SeekerPage.this, SeekerPage.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.VIWEITEM) {
            Intent intent = new Intent(SeekerPage.this, ViewItem.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
