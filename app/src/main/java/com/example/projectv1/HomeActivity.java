package com.example.projectv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private DBHelper  firebaseAuth ; //lina



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = DBHelper.getInstance(); //lina


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
