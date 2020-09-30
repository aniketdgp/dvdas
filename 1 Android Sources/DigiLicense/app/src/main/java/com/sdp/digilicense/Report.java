package com.sdp.digilicense;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class Report extends AppCompatActivity {

    Button Submit;
    EditText Reason,Location;
    TextView ScreenName;
    String Reason_Holder, Date_Holder, Time_Holder, Location_holder;
    String finalResult;
    String HttpURL = "http://dvdas.dx.am/php_file/User/userreport.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        //Setting Variable Connection with Elements
        ScreenName = (TextView) findViewById(R.id.screen_name);
        Reason = (EditText) findViewById(R.id.reason);
        Location = (EditText) findViewById(R.id.location);
        Submit = (Button) findViewById(R.id.btn_reportdata);


        //Setting ScreenName
        ScreenName.setText("Submit Report");

        //Getting user name from Session
        final UserSession user = new UserSession(Report.this);
        user_name = user.getUsername();


        //Adding Click Listener on button.
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {




                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(user_name,Reason_Holder, Date_Holder, Time_Holder, Location_holder);

                } else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(Report.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }


            }
        });


    }


    public void CheckEditTextIsEmptyOrNot() {

        //Getting Current Date
        java.util.Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);

        //Getting Current time
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("hh:mm aa");
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String localTime = date.format(currentLocalTime);




        Reason_Holder = Reason.getText().toString();
        Date_Holder = formattedDate;
        Time_Holder = localTime;
        Location_holder = Location.getText().toString();

        if (TextUtils.isEmpty(Reason_Holder) || TextUtils.isEmpty(Date_Holder) || TextUtils.isEmpty(Time_Holder) || TextUtils.isEmpty(Location_holder)) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }

    }


    public void UserRegisterFunction(final String username, final String reason, final String date, final String time, final String location) {

        class UserRegisterFunctionClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Report.this, "Sending Your Report", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(Report.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username", params[0].toLowerCase());

                hashMap.put("reason", params[1]);

                hashMap.put("date", params[2]);

                hashMap.put("time", params[3]);

                hashMap.put("location", params[4]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(username, reason, date, time, location);
    }

}