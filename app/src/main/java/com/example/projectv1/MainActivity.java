package com.example.projectv1;
////
//maha

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  MainActivity extends AppCompatActivity {
    EditText username, password, email;
    Button register, login;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        register = findViewById(R.id.btnregister);
        login = findViewById(R.id.btnlogin);
        email = findViewById(R.id.email1);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String uemail = email.getText().toString();
                String regex = "^(.+)@(.+)$"; // to validate email

                if (user.equals("") )
                {  Toast.makeText(MainActivity.this, "Please enter User name", Toast.LENGTH_SHORT).show();}
                else if (uemail.equals("")) {Toast.makeText(MainActivity.this, "Please enter Email ", Toast.LENGTH_SHORT).show();  }
                else if ( pass.equals("")) { Toast.makeText(MainActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show(); }
                else if (user.length() < 6) {
                    Toast.makeText(MainActivity.this, "User name should be at least 6 characters ", Toast.LENGTH_SHORT).show();
                } else if (!uemail.matches(regex)) {
                    Toast.makeText(MainActivity.this, "Please enter a correct email ", Toast.LENGTH_SHORT).show();
                } else if (pass.length() < 8 || !isCapital(pass)) {
                    Toast.makeText(MainActivity.this, "Password should be at least 8 characters/numbers and contains a capital letter ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUser = DB.checkUsername(user);
                    Boolean checkEmail = DB.checkEmail(uemail);

                    if(!checkUser && !checkEmail){
                        Boolean insert = DB.insertData(user, pass, uemail);
                        if(insert){
                            Toast.makeText(MainActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Already exists! please log in instead",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    @SuppressLint("SuspiciousIndentation")
    public boolean isCapital(String str)
    {
        int count = 0;
        for ( int i = 0 ; i < str.length() ; i++)
        {
            if ( Character.isUpperCase(str.charAt(i))) {
                count++;
            }
        }
        if ( count== 0)
        {
            return false;}
        else
        {
            return true;}
    }
}

// lina
