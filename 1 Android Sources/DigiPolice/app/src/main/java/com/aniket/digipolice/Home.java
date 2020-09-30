package com.aniket.digipolice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    ImageButton Logout;
    TextView ScreenName;
    String user_name;
    ImageButton ViewLicense,Report,FineUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_home);

        //Setting Variable connection
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);
        ScreenName = (TextView)findViewById(R.id.screen_name);
        ViewLicense = (ImageButton)findViewById(R.id.btn_viewlicense);
        Report = (ImageButton)findViewById(R.id.btn_report);
        FineUser = (ImageButton) findViewById(R.id.btn_fineuser);


        //Getting user name from Session
        final UserSession user = new UserSession(Home.this);
        user_name = user.getUsername();


        //Setting Screen Name
        ScreenName.setText("Welcome "+user_name);


        //Logout Button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                //Delete Session Data
                UserSession user =new UserSession(Home.this);
                user.remove();
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
                Toast.makeText(Home.this, "Log Out Successfully", Toast.LENGTH_LONG).show();

            }
        });



        //View License ImageButton
        ViewLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, UserLicense.class);
                startActivity(intent);

            }
        });


        //View Report ImageButton
        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, Report.class);
                startActivity(intent);

            }
        });


        //FineUser ImageButton
        FineUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, FineUser.class);
                startActivity(intent);

            }
        });





    }
}
