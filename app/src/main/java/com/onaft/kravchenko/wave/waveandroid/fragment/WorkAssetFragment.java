package com.onaft.kravchenko.wave.waveandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onaft.kravchenko.wave.waveandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkAssetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkAssetFragment extends Fragment {
    private Context mContext;
    public static final String TAG = "WorkAssetFragment";


    public WorkAssetFragment() {
        // Required empty public constructor
    }


    public static WorkAssetFragment newInstance(Context context) {
        WorkAssetFragment fragment = new WorkAssetFragment();
        Bundle args = new Bundle();
        fragment.setContext(context);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_asset, container, false);
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
