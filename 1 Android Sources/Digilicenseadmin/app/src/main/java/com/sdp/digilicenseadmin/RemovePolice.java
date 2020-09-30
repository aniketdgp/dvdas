package com.sdp.digilicenseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class RemovePolice extends AppCompatActivity {

    TextView ScreenName;
    Button RemoveUser;
    ImageButton Logout;
    EditText UserName;
    Boolean CheckEditText;
    String UserName_Holder;
    String finalResult ;
    String HttpURL = "http://dvdas.dx.am/php_file/Admin/removepolice.php";
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_police);

        //Setting variable Connection
        ScreenName = (TextView)findViewById(R.id.screen_name);
        RemoveUser = (Button) findViewById(R.id.btn_removeuser);
        UserName = (EditText)findViewById(R.id.removeuser_username);
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);

        //Setting name of the Screen
        ScreenName.setText("Remove Police");



        //Logout Button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                //Delete Session Data
                UserSession user =new UserSession(RemovePolice.this);
                user.remove();
                Intent intent = new Intent(RemovePolice.this, Login.class);
                startActivity(intent);
                Toast.makeText(RemovePolice.this, "Log Out Successfully", Toast.LENGTH_LONG).show();

            }
        });



        RemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    editLicenseTypeFunction(UserName_Holder);

                }
                else {

                    Toast.makeText(RemovePolice.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


    }



    public void CheckEditTextIsEmptyOrNot(){


        UserName_Holder = UserName.getText().toString();


        if(TextUtils.isEmpty(UserName_Holder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }


    public void editLicenseTypeFunction(final String username){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(RemovePolice.this,"Deleting Police Account",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(RemovePolice.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(username);
    }




}
