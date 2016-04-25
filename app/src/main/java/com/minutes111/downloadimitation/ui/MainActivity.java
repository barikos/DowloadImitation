package com.minutes111.downloadimitation.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.minutes111.downloadimitation.Const;
import com.minutes111.downloadimitation.R;
import com.minutes111.downloadimitation.ui.Adapter.MyRecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private BroadcastReceiver mReceiver;
    private MyRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, ServiceDownload.class));
        mProgressBar.setVisibility(View.VISIBLE);

        mReceiver = getReceiver();
        registerReceiver(mReceiver, new IntentFilter(Const.BROADCAST_ACTION));

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mReceiver);
        super.onStop();
    }


    private BroadcastReceiver getReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mProgressBar.setVisibility(View.GONE);
                ArrayList arrayList = intent.getIntegerArrayListExtra(Const.ATTR_IMAGES);
                mAdapter = new MyRecyclerAdapter(arrayList, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }
        };
    }
}
