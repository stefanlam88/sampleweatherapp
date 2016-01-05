package com.stefanapp.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stefanapp.weather.R;
import com.stefanapp.weather.ViewHolder.CityViewHolder;
import com.stefanapp.weather.ViewHolder.HoursViewHolder;
import com.stefanapp.weather.classes.City;
import com.stefanapp.weather.classes.Hours;

import java.util.List;

/**
 * Created by stefanlam88 on 21/12/15.
 */
public class HoursViewAdapter extends RecyclerView.Adapter<HoursViewHolder> {
    private Context mContext;
    private List<Hours> feedItemList;

    public HoursViewAdapter(Context context, List<Hours> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public HoursViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hour_row, null);

        HoursViewHolder viewHolder = new HoursViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HoursViewHolder customViewHolder, int i) {
        final Hours item = feedItemList.get(i);

        //Setting text view title
        customViewHolder.time.setText(item.time);
        customViewHolder.title.setText(item.tempC);
        customViewHolder.desc.setText(item.weatherDesc);

    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }
}
