package com.example.mykart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DbClassMyKart extends SQLiteOpenHelper {
    String name,userName,email,password,mobileNumber,login;
    SQLiteDatabase dbReader,dbWriter;
    ContentValues values;
    Cursor c;
    String userFound;

    public DbClassMyKart(@Nullable Context context) {
        super(context, "MyKart", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDb) {
        MyDb.execSQL("CREATE TABLE UserAccounts(name text,userName text,email text,password text,mobileNumber text,login text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDb, int i, int i1) {
        MyDb.execSQL("DROP TABLE IF EXISTS Interns");
        onCreate(MyDb);
    }
    public void addNewUser(String name,String userName,String email,String password,String mobileNumber,String login){
        dbWriter=this.getWritableDatabase();
        values=new ContentValues();
        values.put("name",name);
        values.put("userName",userName);
        values.put("email",email);
        values.put("password",password);
        values.put("mobileNumber",mobileNumber);
        values.put("login",login);
        dbWriter.insert("UserAccounts",null,values);
    }
    public void verify(String userAccess,String pass){
        dbReader=this.getReadableDatabase();
        try{
            c= dbReader.rawQuery("SELECT * FROM UserAccounts WHERE mobileNumber=? ",new String[]{userAccess});
            c.moveToNext();
            name=c.getString(0);
            userName=c.getString(1);
            email=c.getString(2);
            password=c.getString(3);
            mobileNumber=c.getString(4);
            login=c.getString(5);
            userFound="true";
            c.close();
        }catch (Exception e){
            userFound="false";
        }
    }
    public void getData(String mobile) {
        dbReader = this.getReadableDatabase();
        c = dbReader.rawQuery("SELECT * FROM UserAccounts WHERE mobileNumber=? ", new String[]{mobile});
        c.moveToNext();
        name = c.getString(0);
        userName = c.getString(1);
        email = c.getString(2);
        password = c.getString(3);
        mobileNumber = c.getString(4);
        login = c.getString(5);
        c.close();
    }

}

