package com.sdp.digilicenseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    TextView ScreenName;
    ImageButton Logout;
    ImageButton EditLicense_data,EditLicense_type,RemoveUser,AddVehicle,AddPolice,RemovePolice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Setting Variable Connection with Layout
        ScreenName = (TextView)findViewById(R.id.screen_name);
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);
        EditLicense_data = (ImageButton)findViewById(R.id.btn_editLicense_data);
        EditLicense_type = (ImageButton)findViewById(R.id.btn_editLicense_type);
        RemoveUser = (ImageButton)findViewById(R.id.btn_deletelicense);
        AddVehicle = (ImageButton)findViewById(R.id.btn_addvehicle);
        AddPolice = (ImageButton)findViewById(R.id.btn_addpolice);
        RemovePolice = (ImageButton)findViewById(R.id.btn_removepolice);

        //Setting ScreenName
        ScreenName.setText("Admin LoggedIn");



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

        //Add license_data Button
        EditLicense_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, Add_LicenseData.class);
                startActivity(intent);

            }
        });

        //Add license_type Button
        EditLicense_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, Edit_LicenseType.class);
                startActivity(intent);

            }
        });

        //Add Remove License Button
        RemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, RemoveUser.class);
                startActivity(intent);

            }
        });

        //Add Vehicle Button
        AddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, Add_UserVehicle.class);
                startActivity(intent);

            }
        });

        //Add Police Button
        AddPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, Add_Police.class);
                startActivity(intent);

            }
        });


        //Remove Police Button
        RemovePolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, RemovePolice.class);
                startActivity(intent);

            }
        });





    }
}



