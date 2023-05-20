package com.example.projectv1;
//maha//

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;

public class ViewItem extends AppCompatActivity {
    RecyclerView recyclerView ;
    ArrayList<String> item , phone , price , category ;
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

        recyclerView = findViewById(R.id.recycleview);

        adapter = new MyAdapter(this , item , price, phone , category);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();




    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
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
//bylina
