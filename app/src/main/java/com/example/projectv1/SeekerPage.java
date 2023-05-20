package com.example.projectv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SeekerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_page);
    }


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
//bylina
