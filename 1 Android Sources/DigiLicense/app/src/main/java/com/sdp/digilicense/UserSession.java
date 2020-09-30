package com.sdp.digilicense;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private  String username;
    Context context;
    SharedPreferences sharedPreferences;


    public void remove(){

        sharedPreferences.edit().clear().commit();

    }


    public String getUsername() {
        username = sharedPreferences.getString("userdata","");
        return username;

    }

    public void setUsername(String username) {
        this.username = username;
        sharedPreferences.edit().putString("userdata",username).commit();
    }





    public UserSession(Context context){

        this.context = context;
        sharedPreferences  = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);

    }



}
