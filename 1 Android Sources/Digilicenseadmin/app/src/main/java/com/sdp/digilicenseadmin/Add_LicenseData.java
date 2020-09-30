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

public class Add_LicenseData extends AppCompatActivity {

    TextView ScreenName;
    ImageButton Logout;
    Button Submit;
    EditText Username,State,Dlnumber,Fullname,Sdwhof,Dob,Age,Dateofissue,Validtill,Bloodgroup,Permanentaddress,Currentaddress;
    String Username_Holder,State_Holder,Dlnumber_Holder,Fullname_Holder,Sdwhof_Holder,Dob_Holder,Age_Holder,Dateofissue_Holder,Validtill_Holder,Bloodgroup_Holder,Permanentaddress_Holder,Currentaddress_Holder;


    String finalResult ;
    String HttpURL = "http://dvdas.dx.am/php_file/Admin/db_ul.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__license_data);

        //Setting Variable Connection with Layout
        ScreenName = (TextView)findViewById(R.id.screen_name);
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);
        Submit = (Button)findViewById(R.id.submit);
        Username = (EditText) findViewById(R.id.username);
        State = (EditText) findViewById(R.id.state);
        Dlnumber = (EditText) findViewById(R.id.dlno);
        Fullname = (EditText) findViewById(R.id.fullname);
        Sdwhof = (EditText) findViewById(R.id.sdwhof);
        Dob = (EditText) findViewById(R.id.dob);
        Age = (EditText) findViewById(R.id.age);
        Dateofissue = (EditText) findViewById(R.id.dateofissue);
        Validtill = (EditText) findViewById(R.id.validtill);
        Bloodgroup = (EditText) findViewById(R.id.bloodgroup);
        Permanentaddress = (EditText) findViewById(R.id.permanentaddress);
        Currentaddress = (EditText) findViewById(R.id.currentaddress);


        //Setting ScreenName
        ScreenName.setText("Add License Data");


        //Adding Click Listener on button.
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(Username_Holder,State_Holder,Dlnumber_Holder,Fullname_Holder,Sdwhof_Holder,Dob_Holder,Age_Holder,Dateofissue_Holder,Validtill_Holder,Bloodgroup_Holder,Permanentaddress_Holder,Currentaddress_Holder);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(Add_LicenseData.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }


            }
        });



    }





    public void CheckEditTextIsEmptyOrNot(){

        Username_Holder = Username.getText().toString();
        State_Holder = State.getText().toString();
        Dlnumber_Holder = Dlnumber.getText().toString();
        Fullname_Holder = Fullname.getText().toString();
        Sdwhof_Holder = Sdwhof.getText().toString();
        Dob_Holder = Dob.getText().toString();
        Age_Holder = Age.getText().toString();
        Dateofissue_Holder = Dateofissue.getText().toString();
        Validtill_Holder = Validtill.getText().toString();
        Bloodgroup_Holder = Bloodgroup.getText().toString();
        Permanentaddress_Holder = Permanentaddress.getText().toString();
        Currentaddress_Holder = Currentaddress.getText().toString();




        if(TextUtils.isEmpty(Username_Holder) || TextUtils.isEmpty(State_Holder) || TextUtils.isEmpty(Dlnumber_Holder) || TextUtils.isEmpty(Fullname_Holder) || TextUtils.isEmpty(Sdwhof_Holder) || TextUtils.isEmpty(Dob_Holder) || TextUtils.isEmpty(Age_Holder) || TextUtils.isEmpty(Dateofissue_Holder) || TextUtils.isEmpty(Validtill_Holder) || TextUtils.isEmpty(Bloodgroup_Holder) || TextUtils.isEmpty(Permanentaddress_Holder) || TextUtils.isEmpty(Currentaddress_Holder) )
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String username, final String state, final String dlnumber, final String fullname ,final String sdwhof, final String dob,final String age,final String dateofissue,final String validtill,final String bloodgroup, final String permanentaddress ,final String currentaddress){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Add_LicenseData.this,"Updating User License",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(Add_LicenseData.this,httpResponseMsg, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0]);
                hashMap.put("state",params[1]);
                hashMap.put("dlno",params[2]);
                hashMap.put("fullname",params[3]);
                hashMap.put("sdwhof",params[4]);
                hashMap.put("dob",params[5]);
                hashMap.put("age",params[6]);
                hashMap.put("dateofissue",params[7]);
                hashMap.put("validtill",params[8]);
                hashMap.put("bloodgroup",params[9]);
                hashMap.put("permanentaddress",params[10]);
                hashMap.put("currentaddress",params[11]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(username,state,dlnumber,fullname,sdwhof,dob,age,dateofissue,validtill,bloodgroup,permanentaddress,currentaddress);
    }



}
