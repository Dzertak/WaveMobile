package com.onaft.kravchenko.wave.waveandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.onaft.kravchenko.wave.waveandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SyncDateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SyncDateFragment extends Fragment {

    private Context mContext;
    public static final String TAG = "SyncDateFragment";

    public SyncDateFragment() {
        // Required empty public constructor
    }


    public static SyncDateFragment newInstance(Context context) {
        SyncDateFragment fragment = new SyncDateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setContext(context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sync_date, container, false);
    }


}
