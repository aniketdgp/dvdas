package com.sdp.digilicense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ViewLicense extends AppCompatActivity {

    ImageButton Logout;
    String user_name;
    TextView ScreenName;
    String finalResult ;
    String HttpURL = "http://dvdas.dx.am/php_file/Getdata_User/fetch_licensedata.php";
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    TextView State_vl,Dlno_vl,Fullname_vl,Sdwhof_vl,Dob_vl,Age_vl,Dateofissue_vl,Validtill_vl,Bloodgroup_vl,Permanentaddress_vl,Currentaddress_vl;
    TextView Mcwg_vl,Mgv_vl,Lmv_vl,Hmv_vl,Hgmv_vl,Hpmv_vl;
    String Output;
    String[] Outputarr;
    String str_state,str_dlno,str_fullname,str_sdwhof,str_dob,str_age,str_dateofissue,str_validtill,str_bloodgroup,str_permanentaddress,str_currentaddress;
    String str_mcwg,str_mgv,str_lmv,str_hmv,str_hgmv,str_hpmv;
    ScrollView SCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_license);


        //Setting Variable connection
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);
        ScreenName =(TextView)findViewById(R.id.screen_name);
        State_vl =(TextView)findViewById(R.id.vl_state);
        Dlno_vl =(TextView)findViewById(R.id.vl_dlno);
        Fullname_vl =(TextView)findViewById(R.id.vl_fullname);
        Sdwhof_vl =(TextView)findViewById(R.id.vl_sdof);
        Dob_vl =(TextView)findViewById(R.id.vl_dob);
        Age_vl =(TextView)findViewById(R.id.vl_age);
        Dateofissue_vl =(TextView)findViewById(R.id.vl_dateofissue);
        Validtill_vl =(TextView)findViewById(R.id.vl_validtill);
        Bloodgroup_vl =(TextView)findViewById(R.id.vl_bloodgroup);
        Permanentaddress_vl =(TextView)findViewById(R.id.vl_permanentaddress);
        Currentaddress_vl=(TextView)findViewById(R.id.vl_currentaddress);

        Mcwg_vl=(TextView)findViewById(R.id.vl_mcwg);
        Mgv_vl=(TextView)findViewById(R.id.vl_mgv);
        Lmv_vl=(TextView)findViewById(R.id.vl_lmv);
        Hmv_vl=(TextView)findViewById(R.id.vl_hmv);
        Hgmv_vl=(TextView)findViewById(R.id.vl_hgmv);
        Hpmv_vl=(TextView)findViewById(R.id.vl_hpmv);

        SCV = (ScrollView) findViewById(R.id.ma_scrollView);





        //Getting user name from Session
        final UserSession user = new UserSession(ViewLicense.this);
        user_name = user.getUsername();

        //setting ViewLicense Function
        ViewLicense(user_name);

        //setting Screen Name
        ScreenName.setText(user_name+"'s License");



        //Logout Button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
                //Delete Session Data
                UserSession user =new UserSession(ViewLicense.this);
                user.remove();
                Intent intent = new Intent(ViewLicense.this, Login.class);
                startActivity(intent);
                Toast.makeText(ViewLicense.this, "Log Out Successfully", Toast.LENGTH_LONG).show();

            }
        });

        SCV.setVisibility(View.INVISIBLE);









    }



    public void ViewLicense(final String username){

        class ViewLicenseClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(ViewLicense.this,"Getting Your License",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();

                Output = httpResponseMsg;



                    Outputarr = Output.split("#");


                    //setting data in variable from array
                    str_state = Outputarr[1];
                    str_dlno = Outputarr[2];
                    str_fullname = Outputarr[3];
                    str_sdwhof = Outputarr[4];
                    str_dob = Outputarr[5];
                    str_age = Outputarr[6];
                    str_dateofissue = Outputarr[7];
                    str_validtill = Outputarr[8];
                    str_bloodgroup = Outputarr[9];
                    str_permanentaddress = Outputarr[10];
                    str_currentaddress = Outputarr[11];

                    str_mcwg = Outputarr[12];
                    str_mgv = Outputarr[13];
                    str_lmv = Outputarr[14];
                    str_hmv = Outputarr[15];
                    str_hgmv = Outputarr[16];
                    str_hpmv = Outputarr[17];


                    if(str_state.equals("Data Not Found")){
                        SCV.setVisibility(View.INVISIBLE);
                        ScreenName.setText("Contact RTO Admin");
                    }
                    else {
                        SCV.setVisibility(View.VISIBLE);
                    }






                    //setting data in textview
                    State_vl.setText("("+str_state+")");
                    Dlno_vl.setText(str_dlno);
                    Fullname_vl.setText(str_fullname);
                    Sdwhof_vl.setText("S/D/W/H Of: "+str_sdwhof);
                    Dob_vl.setText("DOB: "+str_dob);
                    Age_vl.setText("AGE: "+str_age);
                    Dateofissue_vl.setText("Date Of Issue: "+str_dateofissue);
                    Validtill_vl.setText("Valid Till: "+str_validtill);
                    Bloodgroup_vl.setText("Blood Group: "+str_bloodgroup);
                    Permanentaddress_vl.setText(str_permanentaddress);
                    Currentaddress_vl.setText(str_currentaddress);

                    Mcwg_vl.setText(str_mcwg);
                    Mgv_vl.setText(str_mgv);
                    Lmv_vl.setText(str_lmv);
                    Hmv_vl.setText(str_hmv);
                    Hgmv_vl.setText(str_hgmv);
                    Hpmv_vl.setText(str_hpmv);



                   //Toast.makeText(ViewLicense.this,httpResponseMsg,Toast.LENGTH_LONG).show();








            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        ViewLicenseClass viewLicenseClass = new ViewLicenseClass();
        viewLicenseClass.execute(username);
    }



}
