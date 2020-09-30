package com.sdp.digilicenseadmin;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Edit_LicenseType extends AppCompatActivity {

    TextView ScreenName;
    TextView UserName,LicenseType;
    Button Submit;
    String UserName_Holder,LicenseType_Holder;
    Boolean CheckEditText;

    String finalResult ;
    String HttpURL = "http://dvdas.dx.am/php_file/Admin/db_ult.php";
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__license_type);

        //Setting variable Connection
        ScreenName = (TextView)findViewById(R.id.screen_name);
        Submit = (Button)findViewById(R.id.btn_submit);
        UserName = (TextView)findViewById(R.id.edit_license_type_username);
        LicenseType = (TextView)findViewById(R.id.edit_license_type);


        //Setting the ScreenName
        ScreenName.setText("Edit License Type");



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    editLicenseTypeFunction(UserName_Holder, LicenseType_Holder);

                }
                else {

                    Toast.makeText(Edit_LicenseType.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


    }


    public void CheckEditTextIsEmptyOrNot(){


        UserName_Holder = UserName.getText().toString();
        LicenseType_Holder = LicenseType.getText().toString();


        if(TextUtils.isEmpty(UserName_Holder) || TextUtils.isEmpty(LicenseType_Holder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }


    public void editLicenseTypeFunction(final String username, final String licensetype){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Edit_LicenseType.this,"Updating License Type",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(Edit_LicenseType.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0]);
                hashMap.put("licensetype",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(username,licensetype);
    }






}
