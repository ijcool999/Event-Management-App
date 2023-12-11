package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button bt;
    TextView tv;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        bt=findViewById(R.id.logOut);
        tv=findViewById(R.id.textView);
        user=auth.getCurrentUser();
        if(user==null){
            Intent I= new Intent(getApplicationContext(), Login.class);
            startActivity(I);
            finish();
        }
    }

    public void signs(View view){
        FirebaseAuth.getInstance().signOut();
        Intent I= new Intent(getApplicationContext(), Login.class);
        startActivity(I);
        finish();
    }

    public void cal(View view){
        Intent I= new Intent(MainActivity.this,Calendar.class);
        startActivity(I);
        finish();
    }

    public void list(View view){
        Intent I= new Intent(MainActivity.this,MainActivity3.class);
        startActivity(I);
        finish();
    }
}