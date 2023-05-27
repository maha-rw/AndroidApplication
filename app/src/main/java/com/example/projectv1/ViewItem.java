package com.example.projectv1;
//*
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewItem extends AppCompatActivity {
    RecyclerView recyclerView ;
    ArrayList<String> item , phone , price , category , availability ;
    String ownerName; /////////////////////:::::::::::::::::::::::::
    DBHelper DB ;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        DB = new DBHelper(this);
        item = new ArrayList<>();
        price = new ArrayList<>();
        phone = new ArrayList<>();
        category = new ArrayList<>();
        ownerName = LoginActivity.UserInL; /////////////////////:::::::::::::::::::::::::
        availability = new ArrayList<>();

        recyclerView = findViewById(R.id.recycleview);

        adapter = new MyAdapter(this , item , price, phone , category, availability);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("INSTRUCTIONS");
        builder.setMessage("-You can access the owner's page (to INSERT/DELETE items)\n"+"-Access the Seeker's page (to rent/return an item)\n"+"-Back to the items' page\n"+"-Log out.\n"+"From the three points in Toolbar");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                //Intent intent = new Intent(ViewItem.this, ViewItem.class);
                //startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();




    }

    private void displaydata() {
        Cursor cursor = DB.getALLdata();
        if (cursor.getCount()==0){
            Toast.makeText(ViewItem.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (cursor.moveToNext())
            {
                item.add(cursor.getString(0 )); // check shosho
                phone.add(cursor.getString(1 )); // check shosho
                price.add(cursor.getString(2)); // check shosho
                category.add(cursor.getString(3 )); // check shosho
                availability.add(cursor.getString(5));

            }
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.LOGOUT) {
            Intent intent = new Intent(ViewItem.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.OWNERPAGE) {
            Intent intent = new Intent(ViewItem.this, HomeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.SEEKERPAGE) {
            Intent intent = new Intent(ViewItem.this, SeekerPage.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.VIWEITEM) {
            Intent intent = new Intent(ViewItem.this, ViewItem.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
