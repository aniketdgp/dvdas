package com.aniket.digipolice.Reports;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.aniket.digipolice.Libs.HttpParse;
import com.aniket.digipolice.R;
import com.aniket.digipolice.Report;

import java.util.HashMap;
import java.util.List;

public class ReportAdapter  extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    String HttpURL = "http://dvdas.dx.am/php_file/Police/delete_report.php";
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String finalResult ;
    String reportid;

    private List<Reportdata> list;
    private Context context;


    public ReportAdapter(Context context, List<Reportdata> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_report, parent, false);
        return new ReportAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ReportAdapter.ViewHolder holder, int position) {

        final Reportdata rd = list.get(position);
        holder.username.setText(rd.getUsername());
        holder.date.setText(rd.getDate());
        holder.time.setText(rd.getTime());
        holder.reason.setText(rd.getReason());
        holder.location.setText(rd.getLocation());

        reportid = rd.getUsername()+"##"+rd.getDate()+"##"+rd.getTime();

        //Adding Delete Button To Adapter List
        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Passing Report Id to deleteReport Function
                deleteReport(reportid);
                Intent intent=new Intent(context, Report.class);
                context.startActivity(intent);

                //Kill Report Activity
                Report.getInstance().finish();



            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username,date,time,reason,location;
        public ImageButton DeleteButton;


        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.rp_username);
            date = itemView.findViewById(R.id.rp_date);
            time = itemView.findViewById(R.id.rp_time);
            reason = itemView.findViewById(R.id.reason);
            location = itemView.findViewById(R.id.location);
            DeleteButton = itemView.findViewById(R.id.btn_report_delete);






        }
    }









    public void deleteReport(final String reportid){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

               // progressDialog = ProgressDialog.show(context,"Deleting Report",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

              //  progressDialog.dismiss();

                Toast.makeText(context,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("reportid",params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(reportid);
    }






}
