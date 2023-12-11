package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    EditText email,pass;
    Button register;
    FirebaseAuth mAuth;

    ProgressBar pb;
    TextView loginNow;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent I=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(I);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.et1);
        pass=findViewById(R.id.et2);
        register=findViewById(R.id.bt2);
        pb=findViewById(R.id.progressbar);
        loginNow=findViewById(R.id.loginNow);
    }


    public void loginN(View view){
        Intent I =new Intent(getApplicationContext(), Login.class);
        startActivity(I);
        finish();
    }

    public void registers(View view){
        pb.setVisibility(View.VISIBLE);
        String e,p;
        e=String.valueOf(email.getText());
        p=String.valueOf(pass.getText());

        if (TextUtils.isEmpty(e)){
            Toast.makeText(Registration.this, "Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(p)){
            Toast.makeText(Registration.this, "Password is Invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(e,p)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pb.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Account Created. ",
                                    Toast.LENGTH_SHORT).show();
                            Intent I =new Intent(getApplicationContext(), Login.class);
                            startActivity(I);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}