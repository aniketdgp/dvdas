package com.sdp.digilicense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sdp.digilicense.Veichle.VeichleAdapter;
import com.sdp.digilicense.Veichle.VeichleData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewVehicle extends AppCompatActivity {


    private String url = "http://dvdas.dx.am/php_file/User/display_veh.php";
    TextView ScreenName;
    String user_name;
    private RecyclerView mList;

    private List<VeichleData> VeichelList;
    private RecyclerView.Adapter adapter;
    ImageButton Logout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicle);


        mList = findViewById(R.id.veichel_list);

        VeichelList = new ArrayList<>();
        adapter = new VeichleAdapter(getApplicationContext(), VeichelList);

        mList.setAdapter(adapter);




        //Setting Variable Connection
        ScreenName = (TextView)findViewById(R.id.screen_name);
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);


        //Getting user name from Session
        final UserSession user = new UserSession(ViewVehicle.this);
        user_name = user.getUsername();

        //Setting Screen Name
        ScreenName.setText(user_name+"'s vehicles");



        //Logout Button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                //Delete Session Data
                UserSession user =new UserSession(ViewVehicle.this);
                user.remove();
                Intent intent = new Intent(ViewVehicle.this, Login.class);
                startActivity(intent);
                Toast.makeText(ViewVehicle.this, "Log Out Successfully", Toast.LENGTH_LONG).show();

            }
        });











        getData();


    }

    private void getData() {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            // System.out.println(response.getJSONObject("data"));

                            //JSONObject jab = response.getJSONObject("data");

                            // System.out.println(jab.toString());

                            JSONArray jArray = response.getJSONArray("v_info");


                            for (int i = 0; i < jArray.length() ; i++) {
                                try {

                                    JSONObject jsonObject = jArray.getJSONObject(i);

                                    String username = jsonObject.getString("username");

                                    final UserSession user = new UserSession(ViewVehicle.this);
                                    user_name = user.getUsername();

                                    if(username.equals(user_name)){

                                        String vrn = jsonObject.getString("registration_number");
                                        String vid = jsonObject.getString("veichle_id");
                                        String vbrand = jsonObject.getString("veichle_brand");
                                        String vtype = jsonObject.getString("veichle_type").toLowerCase();

                                        VeichleData vd = new VeichleData();
                                        vd.setRegistrationNumber("Registration Number : "+vrn);
                                        vd.setVeichleId("Veichle id : "+vid);
                                        vd.setVeichleBrand("Veichle Brand : "+vbrand);
                                        vd.setVeichleType("Veichle Type : "+vtype);

                                        VeichelList.add(vd);


                                    }





                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}


