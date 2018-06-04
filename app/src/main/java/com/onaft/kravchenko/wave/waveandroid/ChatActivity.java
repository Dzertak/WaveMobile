package com.onaft.kravchenko.wave.waveandroid;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.onaft.kravchenko.wave.waveandroid.fragment.DialogsFragment;

public class ChatActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mFragmentManager.beginTransaction().
                add(R.id.container_chat, DialogsFragment.newInstance("",""),DialogsFragment.TAG).commit();
    }
}
