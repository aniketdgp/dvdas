package com.sdp.digilicenseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    TextView ScreenName;
    EditText UserName,Password;
    Button Login;
    String username,password,UserName_Holder,Password_Holder;
    Boolean CheckEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Setting Variable Connection with Layout
        ScreenName = (TextView)findViewById(R.id.screen_name);
        UserName = (EditText) findViewById(R.id.login_username);
        Password = (EditText) findViewById(R.id.login_password);
        Login = (Button)findViewById(R.id.btn_login);

        //Setting ScreenName
        ScreenName.setText("Admin Login");


        //Session Check
        final UserSession user = new UserSession(Login.this);
        if(user.getUsername()!=""){

            Intent intent = new Intent(Login.this, Home.class);

            intent.putExtra("userdata",user.getUsername());
            startActivity(intent);
            finish();

        }



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    username=UserName.getText().toString();
                    password=Password.getText().toString();

                    if(username.equals("admin")&& password.equals("admin")){

                        //Putting User email in Session
                        UserSession user = new UserSession(Login.this);
                        user.setUsername(username);

                        finish();
                        Intent intent = new Intent(Login.this ,Home.class);
                        startActivity(intent);
                        Toast.makeText(Login.this, "Logged In Successfully.", Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(Login.this, "Wrong User id or Password", Toast.LENGTH_LONG).show();
                    }

                }
                else {

                    Toast.makeText(Login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


    }


    public void CheckEditTextIsEmptyOrNot(){


        UserName_Holder = UserName.getText().toString();
        Password_Holder = Password.getText().toString();


        if(TextUtils.isEmpty(UserName_Holder) || TextUtils.isEmpty(Password_Holder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }

}
