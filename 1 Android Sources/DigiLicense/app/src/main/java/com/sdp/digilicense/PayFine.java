package com.sdp.digilicense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class PayFine extends AppCompatActivity {

    TextView ScreenName;
    ImageButton Logout;

    Button PayFine;
    EditText FineAmount;
    String UserName_Holder, PoliceName_Holder, Reason_Holder, Fine_Holder;
    String finalResult ;
    String HttpURL = "http://dvdas.dx.am/php_file/Fine/payfine.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String user_name;
    TextView ViewFine;
    public UserSession user;


    String finalResult2 ;
    String HttpURL2 = "http://dvdas.dx.am/php_file/Fine/duefine.php";
    ProgressDialog progressDialog2;
    HashMap<String,String> hashMap2 = new HashMap<>();
    HttpParse httpParse2 = new HttpParse();
    String Output2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_fine);

        user = new UserSession(this);


        //Setting Variable connection
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);
        ScreenName = (TextView)findViewById(R.id.screen_name);
        FineAmount = (EditText) findViewById(R.id.fine_amount);
        PayFine = (Button)findViewById(R.id.btn_fine_submit);
        ViewFine = (TextView)findViewById(R.id.finetopay);





        ViewFine.setText("");


        //Getting user name from Session
        final UserSession user = new UserSession(PayFine.this);
        user_name = user.getUsername();

        //Setting Screen Name
        ScreenName.setText(user_name+" 's Fine");
        //ViewFine.setVisibility(View.INVISIBLE);

        //Get Current Outstanding Due
        GetFineData(user_name);





        //Logout Button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
                //Delete Session Data
                UserSession user =new UserSession(PayFine.this);
                user.remove();
                Intent intent = new Intent(PayFine.this, Login.class);
                startActivity(intent);
                Toast.makeText(PayFine.this, "Log Out Successfully", Toast.LENGTH_LONG).show();

            }
        });


        //Adding Click Listener on button.
        PayFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){


                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    FineFunction(UserName_Holder,PoliceName_Holder, Reason_Holder, Fine_Holder);
                    //Get Updated Outstanding Due
                    GetFineData(user_name);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(PayFine.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }


            }
        });

    }


    public void CheckEditTextIsEmptyOrNot(){

        String username = user.getUsername();
        UserName_Holder = username;
        PoliceName_Holder = "NA";
        Reason_Holder = "Paid";
        Fine_Holder = FineAmount.getText().toString();


        if(TextUtils.isEmpty(UserName_Holder) || TextUtils.isEmpty(PoliceName_Holder) || TextUtils.isEmpty(Reason_Holder) || TextUtils.isEmpty(Fine_Holder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void FineFunction(final String username, final String policename, final String reason, final String fine){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(PayFine.this,"Payment In Process",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(PayFine.this,httpResponseMsg, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0].toLowerCase());

                hashMap.put("policename",params[1].toLowerCase());

                hashMap.put("reason",params[2]);

                hashMap.put("fineamount",params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();
        userRegisterFunctionClass.execute(username,policename,reason,fine);
    }





    public void GetFineData(final String username){

        class GetFine extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                Output2 = httpResponseMsg;
                int len = Output2.length();
                String s = Output2.substring(1,len);



                ViewFine.setText(s);




            }

            @Override
            protected String doInBackground(String... params) {

                hashMap2.put("username",params[0]);
                finalResult2 = httpParse2.postRequest(hashMap2, HttpURL2);
                return finalResult2;
            }
        }

        GetFine getFineclass = new GetFine();
        getFineclass.execute(username);
    }




}
