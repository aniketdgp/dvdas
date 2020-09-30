package com.sdp.digilicenseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Add_Police extends AppCompatActivity {

    TextView ScreenName;
    Button AddPolice;
    EditText UserName, Password;
    String UserName_Holder, Password_Holder;
    String finalResult;
    String HttpURL = "http://dvdas.dx.am/php_file/Admin/add_police.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__police);

        //Setting Screen Connection with Variable
        ScreenName = (TextView) findViewById(R.id.screen_name);
        UserName = (EditText) findViewById(R.id.addpolice_username);
        Password = (EditText) findViewById(R.id.addpolice_password);
        AddPolice = (Button) findViewById(R.id.btn_vehicle_submit);


        //Setting Screen Name
        ScreenName.setText("Add New Police");


        //Adding Click Listener on button.
        AddPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(UserName_Holder, Password_Holder);

                } else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(Add_Police.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }


            }
        });


    }


    public void CheckEditTextIsEmptyOrNot() {

        UserName_Holder = UserName.getText().toString();
        Password_Holder = Password.getText().toString();


        if (TextUtils.isEmpty(UserName_Holder) || TextUtils.isEmpty(Password_Holder)) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }

    }


    public void UserRegisterFunction(final String username, final String password) {

        class UserRegisterFunctionClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Add_Police.this, "Adding Police Account", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(Add_Police.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username", params[0].toLowerCase());

                hashMap.put("user_password", params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(username, password);
    }

}