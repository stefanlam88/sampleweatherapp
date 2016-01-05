package com.stefanapp.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stefanapp.weather.R;
import com.stefanapp.weather.ViewHolder.CityViewHolder;
import com.stefanapp.weather.classes.City;

import java.util.List;

/**
 * Created by stefanlam88 on 21/12/15.
 */
public class CityViewAdapter extends RecyclerView.Adapter<CityViewHolder> {
    private Context mContext;
    private List<City> feedItemList;

    public CityViewAdapter(Context context, List<City> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city_row, null);

        CityViewHolder viewHolder = new CityViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CityViewHolder customViewHolder, int i) {
        final City item = feedItemList.get(i);

        //Setting text view title
        customViewHolder.title.setText(item.city + ", " + item.country);

        if(item.selected){
            customViewHolder.selection.setImageResource(android.R.drawable.star_on);
        }
        else{
            customViewHolder.selection.setImageResource(android.R.drawable.star_off);
        }

        customViewHolder.selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllStar();

                if(!item.selected){
                    item.selected = true;
                }
                else{
                    item.selected = false;
                }
                notifyDataSetChanged();
            }
        });
    }

    private void clearAllStar(){
        for(int i = 0; i < feedItemList.size(); i++){
            feedItemList.get(i).selected = false;
        }
    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }
}
