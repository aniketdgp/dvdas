package com.sdp.digilicense;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    Button Signup, Login;
    EditText UserName, Password, Email, PhoneNo ;
    TextView ScreenName;
    String UserName_Holder, Password_Holder, Email_Holder, PhoneNo_Holder;
    String finalResult ;
    String HttpURL = "http://dvdas.dx.am/php_file/User/signup.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    Runnable refresh;
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);




        //Assign Id'S
        UserName = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Email = (EditText)findViewById(R.id.email);
        PhoneNo = (EditText)findViewById(R.id.phoneno);
        Signup = (Button)findViewById(R.id.btn_signup);
        Login = (Button)findViewById(R.id.btn_refer_login);
        ScreenName = (TextView)findViewById(R.id.screen_name);


        //Setting Screen Name
        ScreenName.setText("SignUp");






        //Adding Click Listener on button.
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){


                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(UserName_Holder,Password_Holder, Email_Holder, PhoneNo_Holder);


                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(Signup.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }



            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);


            }
        });

    }

    public void CheckEditTextIsEmptyOrNot(){

        UserName_Holder = UserName.getText().toString();
        Password_Holder = Password.getText().toString();
        Email_Holder = Email.getText().toString();
        PhoneNo_Holder = PhoneNo.getText().toString();


        if(TextUtils.isEmpty(UserName_Holder) || TextUtils.isEmpty(Password_Holder) || TextUtils.isEmpty(Email_Holder) || TextUtils.isEmpty(PhoneNo_Holder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String username, final String password, final String email, final String phoneno){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Signup.this,"Creating User Account",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();
                checkSession();

                Toast.makeText(Signup.this,httpResponseMsg, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0].toLowerCase());

                hashMap.put("user_password",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("phoneno",params[3]);

                //Adding User To session so that user signed up gets logged in automatically
                final UserSession user = new UserSession(Signup.this);
                user.setUsername(params[0].toLowerCase());


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();
        userRegisterFunctionClass.execute(username,password,email,phoneno);
    }


    void checkSession(){
        //Session Check
        final UserSession user = new UserSession(Signup.this);
        if(user.getUsername()!=""){

            Intent intent = new Intent(Signup.this, Home.class);
            intent.putExtra("email",user.getUsername());
            startActivity(intent);
            finishAffinity();
        }
    }

}