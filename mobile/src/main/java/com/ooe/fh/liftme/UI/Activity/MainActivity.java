package com.ooe.fh.liftme.UI.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Adapters.SectionsPagerAdapter;
import com.ooe.fh.liftme.UI.Layout.Elements.CustomViewPager;
import com.ooe.fh.liftme.application.AppClass;
import com.ooe.fh.liftme.utils.Listeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, MessageApi.MessageListener, DataApi.DataListener {

    @Bind(R.id.tablayout)
    TabLayout tablayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.container)
    CustomViewPager mViewPager;

    //Adapters
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private String mNodeId;
    private GoogleApiClient mGoogleApiClient;

    private Listeners.OnMessageReceivedListener messageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standard_activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        tablayout.addTab(tablayout.newTab().setText("Create"));
        tablayout.addTab(tablayout.newTab().setText("Start"));
        tablayout.addTab(tablayout.newTab().setText("Overview"));

        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

        //AppClass.overviewTraining_Listitem_models = new ArrayList<OverviewTraining_Listitem_Model>();

        initializeGoogleApiClient();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mGoogleApiClient.connect();
        getBestNode();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient != null) {
            Wearable.DataApi.removeListener(mGoogleApiClient, this);
            Wearable.MessageApi.removeListener(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public CustomViewPager getmViewPager(){
        return mViewPager;
    }

    private void initializeGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    public String getNodeId() {
        return mNodeId;
    }

    public void setOnMessageReceivedListener(Listeners.OnMessageReceivedListener messageListener) {
        this.messageListener = messageListener;
    }

    private void getBestNode() {

        // This must be an asynchronous call with callback
        // Otherwise .getConnectedNodes().await will return it immediately,
        // but must not called on the UI thread
        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(@NonNull NodeApi.GetConnectedNodesResult getConnectedNodesResult) {

                // Just get the first connected node
                // We assume that there is just 1 Wearable connected
                List<Node> nodes = getConnectedNodesResult.getNodes();
                if (nodes.size() > 0) {
                    mNodeId = nodes.get(0).getId();
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Wearable.DataApi.addListener(mGoogleApiClient, this);
        Wearable.MessageApi.addListener(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) { }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {

        // TODO Handle data synchronisation from watch
        for (DataEvent event : dataEventBuffer) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                // DataItem changed
                DataItem item = event.getDataItem();
            }
        }
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        if (messageListener != null) {
            messageListener.onMessageReceived(messageEvent);
        }
    }
}
