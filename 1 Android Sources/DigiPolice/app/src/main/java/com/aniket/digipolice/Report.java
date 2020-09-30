package com.aniket.digipolice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.aniket.digipolice.Reports.ReportAdapter;
import com.aniket.digipolice.Reports.Reportdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity {


    private String url = "http://dvdas.dx.am/php_file/Police/display_Report.php";
    TextView ScreenName;
    String user_name;
    private RecyclerView mList;

    private List<Reportdata> ReportList;
    private RecyclerView.Adapter adapter;
    ImageButton Logout;

    static Report reportactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        mList = findViewById(R.id.report_list);

        ReportList = new ArrayList<>();
        adapter = new ReportAdapter(getApplicationContext(), ReportList);

        mList.setAdapter(adapter);




        //Setting Variable Connection
        ScreenName = (TextView)findViewById(R.id.screen_name);
        Logout = (ImageButton)findViewById(R.id.logout_btn_appbar);


        //Setting Activity Connection
        reportactivity = this;

        //Getting user name from Session
        final UserSession user = new UserSession(Report.this);
        user_name = user.getUsername();

        //Setting Screen Name
        ScreenName.setText("Availaible Reports");



        //Logout Button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                //Delete Session Data
                UserSession user =new UserSession(Report.this);
                user.remove();
                Intent intent = new Intent(Report.this, Login.class);
                startActivity(intent);
                Toast.makeText(Report.this, "Log Out Successfully", Toast.LENGTH_LONG).show();

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


                            JSONArray jArray = response.getJSONArray("report_info");


                            for (int i = 0; i < jArray.length() ; i++) {
                                try {

                                    JSONObject jsonObject = jArray.getJSONObject(i);

                                    String username = jsonObject.getString("username");
                                    String date = jsonObject.getString("dates");
                                    String time = jsonObject.getString("times");
                                    String reason = jsonObject.getString("reason");
                                    String location = jsonObject.getString("location");



                                        Reportdata rd = new Reportdata();
                                        rd.setUsername(username);
                                        rd.setDate(date);
                                        rd.setTime(time);
                                        rd.setReason(reason);
                                        rd.setLocation(location);

                                        ReportList.add(rd);






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


    //function to get reportactivity in another class
    public static Report getInstance(){
        return reportactivity;
    }


}