package com.onaft.kravchenko.wave.waveandroid.util.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.onaft.kravchenko.wave.waveandroid.R;
import com.onaft.kravchenko.wave.waveandroid.ShootingActivity;
import com.onaft.kravchenko.wave.waveandroid.model.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ShootingAdapter extends RecyclerView.Adapter<ShootingAdapter.ShootingViewHolder>{


    private List<Event> mEvents;

    public ShootingAdapter(List<Event> events){
        mEvents = events;
    }

    public void setData(List<Event> data) {
        mEvents = data;
    }

    @NonNull
    @Override
    public ShootingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shooting_item, parent, false);
        return new ShootingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShootingViewHolder holder, int position) {
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ShootingActivity.class);
                intent.putExtra("shooting_type", true);
                intent.putExtra("id_shooting", String.valueOf(mEvents.get(holder.getAdapterPosition()).getId_shooting()));
                v.getContext().startActivity(intent);
            }
        });
        holder.title.setText(mEvents.get(holder.getAdapterPosition()).getName());
        holder.description.setText(mEvents.get(holder.getAdapterPosition()).getDescription());
        holder.address.setText(mEvents.get(holder.getAdapterPosition()).getAddress());

        DateFormat df = new SimpleDateFormat("MM/dd HH:mm");

        holder.date_start.setText(df.format(mEvents.get(holder.getAdapterPosition()).getDate_start()));
        holder.date_end.setText(df.format(mEvents.get(holder.getAdapterPosition()).getDate_end()));
    }

    @Override
    public int getItemCount() {
        if (mEvents==null)
            return 0;
        return mEvents.size();
    }

    public class ShootingViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView title, description, address, date_start, date_end;



        public ShootingViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cardView_shooting_item);
            address = (TextView) itemView.findViewById(R.id.textView_shooting_item_address);
            title = (TextView) itemView.findViewById(R.id.textView_shooting_item_title);
            description = (TextView) itemView.findViewById(R.id.textView_shooting_item_description);
            date_start = (TextView) itemView.findViewById(R.id.textView_shooting_item_date_start);
            date_end = (TextView) itemView.findViewById(R.id.textView_shooting_item_date_end);
        }


    }
}
