package com.sdp.digilicense.Veichle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sdp.digilicense.R;

import java.util.List;

public class VeichleAdapter  extends RecyclerView.Adapter<VeichleAdapter.ViewHolder> {
    private List<VeichleData> list;
    private Context context;


    public VeichleAdapter(Context context, List<VeichleData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_veichel, parent, false);
        return new VeichleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VeichleAdapter.ViewHolder holder, int position) {

        VeichleData vd = list.get(position);
        holder.rnum.setText(vd.getRegistrationNumber());
        holder.vid.setText(vd.getVeichleId());
        holder.vtype.setText(vd.getVeichleType());
        holder.vbrand.setText(vd.getVeichleBrand());

        if(vd.getVeichleType().equals("Veichle Type : car")){
            holder.vimg.setImageResource(R.drawable.car);
        }
        else if(vd.getVeichleType().equals("Veichle Type : bike")){
            holder.vimg.setImageResource(R.drawable.bike);
        }
        else{
            holder.vimg.setImageResource(R.drawable.heavy_veichel);
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rnum,vid,vbrand,vtype;
        public ImageView vimg;


        public ViewHolder(View itemView) {
            super(itemView);

            rnum = itemView.findViewById(R.id.veichel_registrationnumber);
            vid = itemView.findViewById(R.id.veichel_id);
            vbrand = itemView.findViewById(R.id.veichel_brand);
            vtype = itemView.findViewById(R.id.veichel_type);
            vimg = itemView.findViewById(R.id.imv_veichel);


        }
    }


}