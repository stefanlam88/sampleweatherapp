package com.stefanapp.weather.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stefanapp.weather.R;


/**
 * Created by stefanlam88 on 21/12/15.
 */
public class CityViewHolder extends RecyclerView.ViewHolder {
    public ImageView selection;
    public TextView title;

    public CityViewHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.city_name);
        this.selection = (ImageView) view.findViewById(R.id.select_item);
    }
}
