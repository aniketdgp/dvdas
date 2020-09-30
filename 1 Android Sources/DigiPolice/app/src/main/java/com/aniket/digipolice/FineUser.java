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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.aniket.digipolice.Libs.HttpParse;
import java.util.HashMap;

public class FineUser extends AppCompatActivity {

    TextView ScreenName;
    ImageButton Logout;

    Button SubmitFine;
    EditText UserName,Reason,FineAmount;
    String UserName_Holder, PoliceName_Holder, Reason_Holder, Fine_Holder;
    String finalResult ;
    String HttpURL = "http://dvdas.dx.am/php_file/Fine/addfine.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_user);


        //Setting Variable connection
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);
        ScreenName = (TextView)findViewById(R.id.screen_name);
        UserName = (EditText) findViewById(R.id.fine_username);
        Reason = (EditText) findViewById(R.id.fine_reason);
        FineAmount = (EditText) findViewById(R.id.fine_amount);
        SubmitFine = (Button)findViewById(R.id.btn_fine_submit);


        //Setting Screen Name
        ScreenName.setText("Fine User");




        //Logout Button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
                //Delete Session Data
                UserSession user =new UserSession(FineUser.this);
                user.remove();
                Intent intent = new Intent(FineUser.this, Login.class);
                startActivity(intent);
                Toast.makeText(FineUser.this, "Log Out Successfully", Toast.LENGTH_LONG).show();

            }
        });



        //Adding Click Listener on button.
        SubmitFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){


                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    FineFunction(UserName_Holder,PoliceName_Holder, Reason_Holder, Fine_Holder);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(FineUser.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }


            }
        });

    }


    public void CheckEditTextIsEmptyOrNot(){

        UserName_Holder = UserName.getText().toString();
        UserSession user = new UserSession(this);
        String policename = user.getUsername();
        PoliceName_Holder = policename;
        Reason_Holder = Reason.getText().toString();
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

                progressDialog = ProgressDialog.show(FineUser.this,"Generating Fine",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(FineUser.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

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



}
