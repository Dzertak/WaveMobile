package com.onaft.kravchenko.wave.waveandroid.util.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.onaft.kravchenko.wave.waveandroid.R;
import com.onaft.kravchenko.wave.waveandroid.ShootingActivity;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Event;
import com.onaft.kravchenko.wave.waveandroid.util.DeleteShootingCallBack;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShootingAdapter extends RecyclerView.Adapter<ShootingAdapter.ShootingViewHolder>{


    private List<Event> mEvents;
    private DataManager mDataManager;
    private Activity mActivity;
    private DeleteShootingCallBack mDeleteShootingCallBack;
    Account mAccount;

    public ShootingAdapter(List<Event> events, Activity activity, DeleteShootingCallBack deleteShootingCallBack){
        mEvents = events;
        mDataManager = DataManager.getInstance();
        mActivity = activity;
        mDeleteShootingCallBack = deleteShootingCallBack;
        mAccount = mDataManager.getmPreferencesManager().loadAccount();
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

        if (mAccount != null)
            if (mAccount.getId_type_access()!=1){
                holder.delete.setVisibility(View.GONE);
            }
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ShootingActivity.class);
                intent.putExtra("shooting_type", true);
                intent.putExtra("event", new Gson().toJson(mEvents.get(holder.getAdapterPosition())));
                v.getContext().startActivity(intent);
            }
        });
        holder.title.setText(mEvents.get(holder.getAdapterPosition()).getName());
        holder.description.setText(mEvents.get(holder.getAdapterPosition()).getDescription());
        holder.address.setText(mEvents.get(holder.getAdapterPosition()).getAddress());

        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH:mm");
        try {
            holder.date_start.setText(df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").parse(mEvents.get(holder.getAdapterPosition()).getShooting().getDate_start())));
            holder.date_end.setText(df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").parse(mEvents.get(holder.getAdapterPosition()).getShooting().getDate_end())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder notifyLocationServices = new AlertDialog.Builder(mActivity, R.style.AppTheme_ChoosePickDelete);
                notifyLocationServices.setTitle("Предупреждение");
                notifyLocationServices.setMessage("Удалить съёмку?");
                notifyLocationServices.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Call<String> stringCall = mDataManager.deleteShooting(String.valueOf(mEvents.get(holder.getAdapterPosition()).getShooting().getId_shooting()));
                        stringCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(v.getContext(), "Съёмка удалена", Toast.LENGTH_SHORT).show();
                                mEvents.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                mDeleteShootingCallBack.deleteShootingCallBack(mEvents);
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(v.getContext(), "Не вышло удалить съёмку. Попробуйте ещё раз", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                notifyLocationServices.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                notifyLocationServices.show();
            }
        });


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
        ImageView delete;



        public ShootingViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cardView_shooting_item);
            address = (TextView) itemView.findViewById(R.id.textView_shooting_item_address);
            title = (TextView) itemView.findViewById(R.id.textView_shooting_item_title);
            description = (TextView) itemView.findViewById(R.id.textView_shooting_item_description);
            date_start = (TextView) itemView.findViewById(R.id.textView_shooting_item_date_start);
            date_end = (TextView) itemView.findViewById(R.id.textView_shooting_item_date_end);
            delete = itemView.findViewById(R.id.imageView_shooting_item_delete);
        }


    }
}
