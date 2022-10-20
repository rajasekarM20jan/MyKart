package com.example.mykart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import controller.controllerLogin;

public class MainActivity extends AppCompatActivity {
    EditText etForMail,etForPassword;
    Button loginButton;
    TextView linkForSignUp;
    DbClassMyKart db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etForMail=findViewById(R.id.etForMail);
        etForPassword=findViewById(R.id.etForPassword);
        loginButton=findViewById(R.id.loginButton);
        linkForSignUp=findViewById(R.id.linkForSignup);

        linkForSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettoSignUp();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etForMail.length()!=0){
                    if(etForPassword.length()!=0){
                        gettoDb();
                    }
                }
            }
        });
    }
    void gettoSignUp(){
        Intent i=new Intent(this,Register.class);
        startActivity(i);
    }
    public void error(){
        etForMail.setError("Invalid Credentials");
        etForPassword.setError("Invalid Credentials");
    }
    public void dashboard(){
        Intent i=new Intent(this,filters.class);
        startActivity(i);
    }
    public void gettoDb(){
        db=new DbClassMyKart(MainActivity.this);
        String name=String.valueOf(etForMail.getText());
        String pass=String.valueOf(etForPassword.getText());
        System.out.println("MyDataTransfer: "+name+pass);
        db.verify(name,pass);
        String user=db.userFound;
        if(user.equals("true")){
            String userMobile=db.mobileNumber;
            String password=db.password;
            controllerLogin c=new controllerLogin(MainActivity.this);
            c.verifyData(userMobile,password,etForMail.getText().toString(),etForPassword.getText().toString());
        }else{
            etForMail.setError("Invalid Credentials");
        }
    }
}