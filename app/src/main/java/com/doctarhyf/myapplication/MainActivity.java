package com.doctarhyf.myapplication;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.doctarhyf.myapplication.frags.FragmentMap;
import com.doctarhyf.myapplication.frags.FragmentSignal;
import com.doctarhyf.myapplication.frags.FragmentInsecHistory;
import com.doctarhyf.myapplication.frags.dummy.DummyContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.doctarhyf.myapplication.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements FragmentSignal.OnFragmentInteractionListener, FragmentInsecHistory.OnListFragmentInteractionListener,
        FragmentMap.OnFragmentInteractionListener {

    private static final String TAG = "TAG";
    private TabLayout mTabs = null;
    private int mCurrentFragIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        mTabs = findViewById(R.id.tabs);
        mTabs.setVisibility(View.GONE);
        mTabs.setupWithViewPager(viewPager);

        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentFragIdx = tab.getPosition();
                Log.e(TAG, "onTabSelected: idx " + tab.getPosition() );
                Log.e(TAG, "onBackPressed: curfidx : " + mCurrentFragIdx + ", FRAG_MAP : " + SectionsPagerAdapter.FRAG_MAP );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        showTab(SectionsPagerAdapter.FRAG_SIGNAL_INSEC);
    }
    public void showTab(int tabIdx){
        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {

                        mCurrentFragIdx = tabIdx;
                        mTabs.getTabAt(tabIdx).select();


                    }
                }, 100);
    }

    private int getStatusBarCol(int tabIdx) {
        int col = R.color.col_main_blue;

        if(tabIdx == SectionsPagerAdapter.FRAG_HISTORY) col = R.color.col_grad_violet;
        if(tabIdx == SectionsPagerAdapter.FRAG_SIGNAL_INSEC) col = R.color.col_main_blue;


        return col;
    }

    @Override
    public void onSignalInsecClicked(Uri uri) {
        Log.e(TAG, "onSignalInsecClicked: " );
    }

    @Override
    public void onShowTabClicked(int fragIdx) {
        showTab(fragIdx);
    }

    @Override
    public void onInsecItemClicked(DummyContent.InsecSignal item) {
        Log.e(TAG, "onInsecItemClicked: -> " + item.toString() );
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if(mCurrentFragIdx == SectionsPagerAdapter.FRAG_MAP){
            showTab(SectionsPagerAdapter.FRAG_SIGNAL_INSEC);
        }else{
            super.onBackPressed();
        }

        Log.e(TAG, "onBackPressed: curfidx : " + mCurrentFragIdx + ", FRAG_MAP : " + SectionsPagerAdapter.FRAG_MAP );
    }
}