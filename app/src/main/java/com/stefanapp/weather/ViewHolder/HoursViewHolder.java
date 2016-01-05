package com.stefanapp.weather.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stefanapp.weather.R;


/**
 * Created by stefanlam88 on 21/12/15.
 */
public class HoursViewHolder extends RecyclerView.ViewHolder {
    public TextView desc;
    public TextView title;
    public TextView time;

    public HoursViewHolder(View view) {
        super(view);

        this.time = (TextView) view.findViewById(R.id.time);
        this.title = (TextView) view.findViewById(R.id.temp);
        this.desc = (TextView) view.findViewById(R.id.desc);
    }
}
