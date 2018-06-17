package com.onaft.kravchenko.wave.waveandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onaft.kravchenko.wave.waveandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListToolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListToolFragment extends Fragment {

    private Context mContext;
    public static final String TAG = "ListToolFragment";
    private CardView mCardViewSync, mCardViewWorkAsset, mCardViewAccountSetting;

    public ListToolFragment() {
        // Required empty public constructor
    }


    public static ListToolFragment newInstance(Context context) {
        ListToolFragment fragment = new ListToolFragment();
        Bundle args = new Bundle();
        fragment.setContext(context);
        fragment.setArguments(args);
        return fragment;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tool, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCardViewSync = getView().findViewById(R.id.cardView_tool_sync);
        mCardViewSync.setOnClickListener(t->{
             getFragmentManager().beginTransaction()
                     .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                     .replace(R.id.container_tools, SyncDateFragment.newInstance(mContext),SyncDateFragment.TAG)
                     .addToBackStack(ListToolFragment.TAG)
                     .commit();
        });
        mCardViewAccountSetting = getView().findViewById(R.id.cardView_tool_account);
        mCardViewAccountSetting.setOnClickListener(t -> {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.container_tools, AccountSettingsFragment.newInstance(mContext),AccountSettingsFragment.TAG)
                    .addToBackStack(ListToolFragment.TAG)
                    .commit();
        });
        mCardViewWorkAsset = getView().findViewById(R.id.cardView_tool_assets);
        mCardViewWorkAsset.setOnClickListener(t -> {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.container_tools, WorkAssetFragment.newInstance(mContext),WorkAssetFragment.TAG)
                    .addToBackStack(ListToolFragment.TAG)
                    .commit();
        });
    }
}
