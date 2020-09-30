package com.sdp.digilicenseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class Add_UserVehicle extends AppCompatActivity {

    TextView ScreenName;

    String UserName_Holder,VRN_Holder,VID_Holder,VBrand_Holder,VType_Holder;
    EditText username,vrn,vid,vbrand,vtype;
    Button submit;
    ImageButton logout;
    String HttpURL = "http://dvdas.dx.am/php_file/Admin/db_add_veichel.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__user_vehicle);

        //Setting Variable Connection with Layout
        ScreenName = (TextView)findViewById(R.id.screen_name);
        username = (EditText) findViewById(R.id.vehicle_username);
        vrn = (EditText) findViewById(R.id.vehicle_registrationNumber);
        vid = (EditText) findViewById(R.id.vehicle_id);
        vtype = (EditText) findViewById(R.id.vehicle_type);
        vbrand = (EditText) findViewById(R.id.vehicle_brand);
        submit = (Button) findViewById(R.id.btn_vehicle_submit);


        //Setting ScreenName
        ScreenName.setText("Add Vehicle");




        //Adding Click Listener on button.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(UserName_Holder,VRN_Holder,VID_Holder,VBrand_Holder,VType_Holder);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(Add_UserVehicle.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }


            }
        });


    }



    public void CheckEditTextIsEmptyOrNot(){

        UserName_Holder =  username.getText().toString();
        VRN_Holder =  vrn.getText().toString();
        VID_Holder =  vid.getText().toString();
        VBrand_Holder =  vbrand.getText().toString();
        VType_Holder = vtype.getText().toString();





        if(TextUtils.isEmpty(UserName_Holder) || TextUtils.isEmpty(VRN_Holder) || TextUtils.isEmpty(VID_Holder) || TextUtils.isEmpty(VBrand_Holder) || TextUtils.isEmpty(VType_Holder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }



    public void UserRegisterFunction(final String username, final String vrn, final String vid, final String vtype ,final String vbrand){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Add_UserVehicle.this,"Adding User Veichel",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(Add_UserVehicle.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0].toLowerCase());
                hashMap.put("registration_number",params[1]);
                hashMap.put("veichle_id",params[2]);
                hashMap.put("veichle_type",params[4]);
                hashMap.put("veichle_brand",params[3]);


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(username,vrn,vid,vtype,vbrand);
    }



}