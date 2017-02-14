package com.doy.nisn;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.doy.nisn.Adapter.ViewPagerAdapter;
import com.doy.nisn.Fragments.BiodataFragment;
import com.doy.nisn.Fragments.NisnFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("CARI NISN");
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new NisnFragment(), "NISN");
        mViewPagerAdapter.addFragment(new BiodataFragment(), "BIODATA");
        viewPager.setAdapter(mViewPagerAdapter);
    }


}
