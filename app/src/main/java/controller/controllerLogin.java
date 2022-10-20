package controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mykart.MainActivity;

public class controllerLogin {
 MainActivity userVerification;
 public controllerLogin(MainActivity mainActivity){
     this.userVerification=mainActivity;
 }
 public void verifyData(String userMobile,String password,String editTextMail,String editTextPass){
     if(editTextMail.equals(userMobile)){
      if(editTextPass.equals(password)){
       SharedPreferences sp= userVerification.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=sp.edit();
       editor.putString("mobile",userMobile);
       editor.commit();
       userVerification.dashboard();
      }else{
          userVerification.error();
      }
     }else{
         userVerification.error();
     }
 }
}
