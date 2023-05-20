package com.example.projectv1;
////

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login;
    DBHelper DB;

    public static String userNameL ;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username2);
        password = findViewById(R.id.password2);
        login = findViewById(R.id.btnlogin2);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                userNameL = user;
                String pass = password.getText().toString();

                if(user.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please enter User name", Toast.LENGTH_SHORT).show();}
                else if (pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();}

                else{
                    Boolean check = DB.checkUsernamePassword(user, pass);
                    if (check){
                        //userNameL = user;
                        Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

// lina

