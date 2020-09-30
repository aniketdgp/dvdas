package com.aniket.digipolice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aniket.digipolice.Libs.HttpParse;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    Button Login;
    EditText UserName;
    EditText Password;
    TextView ScreenName;
    String UserName_Holder,Password_Holder;
    String finalResult ;
    String HttpURL = "http://dvdas.dx.am/php_file/Police/login.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String Username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setting Variable Connection
        ScreenName = findViewById(R.id.screen_name);
        Login = (Button)findViewById(R.id.btn_login);
        UserName = (EditText)findViewById(R.id.login_username);
        Password = (EditText)findViewById(R.id.login_password);
        ScreenName = (TextView)findViewById(R.id.screen_name);

        UserName_Holder = UserName.getText().toString().toLowerCase();
        Password_Holder = Password.getText().toString();


        //Setting Screen Name
        ScreenName.setText("Login");





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

                    UserLoginFunction(UserName_Holder, Password_Holder);

                }
                else {

                    Toast.makeText(Login.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

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


    public void UserLoginFunction(final String username, final String user_password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Login.this,"Logging In",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();


                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){

                    finish();

                    Intent intent = new Intent(Login.this, Home.class);
                    intent.putExtra(Username,username);

                    //Putting User email in Session
                    UserSession user = new UserSession(Login.this);
                    user.setUsername(UserName_Holder);



                    startActivity(intent);

                }
                else{

                    Toast.makeText(Login.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0].toLowerCase());

                hashMap.put("user_password",params[1]);

                System.out.println(params[0]+" "+params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(username,user_password);
    }







}



