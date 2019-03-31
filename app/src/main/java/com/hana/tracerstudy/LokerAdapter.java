package com.hana.tracerstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hana.tracerstudy.Model.ModelLoker;

import java.util.ArrayList;



/**
 * Created by Gentong on 03/02/2018.
 */
public class LokerAdapter extends ArrayAdapter<ModelLoker> implements View.OnClickListener{

    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView nama_loker;
        TextView deskripsi;
        TextView tgl;
        ImageView info;
    }

    public LokerAdapter(ArrayList<ModelLoker> data, Context context) {
        super(context, R.layout.custom_row_loker, data);
        ArrayList<ModelLoker> dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ModelLoker dataModel=(ModelLoker)object;

        switch (v.getId())
        {

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ModelLoker dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_row_loker, parent, false);
            viewHolder.nama_loker = (TextView) convertView.findViewById(R.id.nama_loker);
            viewHolder.deskripsi = (TextView) convertView.findViewById(R.id.deskripsi);
            viewHolder.tgl = (TextView) convertView.findViewById(R.id.tgl);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.nama_loker.setText(dataModel.getNama_loker());
        viewHolder.deskripsi.setText(dataModel.getDeskripsi_loker());
        viewHolder.tgl.setText(String.valueOf(dataModel.getTgl()));


        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}