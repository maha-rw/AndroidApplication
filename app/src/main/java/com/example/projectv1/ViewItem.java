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

    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.menu2 , menu2);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logoutMenu2: {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(new Intent(ViewItem.this , MainActivity.class));
                finish();


            } // lina add this -- > for view item page
            case R.id.control:{
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(new Intent(ViewItem.this , HomeActivity.class));

                finish();


            }


        }
        return super.onOptionsItemSelected(item);
    }
}