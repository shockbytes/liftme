package com.ooe.fh.liftme.UI.Activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Adapters.SectionsPagerAdapter;
import com.ooe.fh.liftme.UI.Layout.Elements.CustomViewPager;
import com.ooe.fh.liftme.application.AppClass;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tablayout)
    TabLayout tablayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.container)
    CustomViewPager mViewPager;

    //Adapters
    private SectionsPagerAdapter mSectionsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standard_activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        tablayout.addTab(tablayout.newTab().setText("Create Training"));
        tablayout.addTab(tablayout.newTab().setText("Start Training"));
        tablayout.addTab(tablayout.newTab().setText("Overview Training"));

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

        AppClass.overviewTraining_Listitem_models = new ArrayList<OverviewTraining_Listitem_Model>();
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
}
