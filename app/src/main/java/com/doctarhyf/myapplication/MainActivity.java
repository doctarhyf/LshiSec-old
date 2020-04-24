package com.doctarhyf.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.doctarhyf.myapplication.ui.main.SectionsPagerAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class MainActivity extends AppCompatActivity implements FragmentSignal.OnFragmentInteractionListener, FragmentInsecHistory.OnListFragmentInteractionListener,
        FragmentMap.OnFragmentInteractionListener {

    private static final String TAG = "TAG";
    private TabLayout mTabs = null;
    private int mCurrentFragIdx = -1;
    private View mRootView;
    private GPSTracker mGpsTracker;
    //RxPermissions rxPermissions;
    String[] perms = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = findViewById(R.id.root);


        new RxPermissions(this)
                .request(perms)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M

                        //mCameraPermsGranted = true;

                    } else {
                        Log.e(TAG, "onCreate: perms refused " );
                    }
                });



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
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, ActivityMyAccount.class));
            }
        });

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        showTab(SectionsPagerAdapter.FRAG_SIGNAL_INSEC);

        mGpsTracker = new GPSTracker(this);

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


        startRecording();


    }

    private boolean mRecordingInsecAudio =false;

    private void startRecording() {

        if( mRecordingInsecAudio ){

            Utils.showSnackWithViewMsgBgColor(mRootView, "Audio already recording!", Snackbar.LENGTH_SHORT, Color.RED);
            return;
        }

        mRecordingInsecAudio = true;
        Utils.showSnackWithViewMsgBgColor(mRootView, "Recording insecurity audio ...", Snackbar.LENGTH_SHORT, Color.GREEN);
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mRecordingInsecAudio = false;

                startUploadingAudio();
            }
        }.start();
    }

    private boolean mUploadingAudio = false;

    private void startUploadingAudio() {

        if(mUploadingAudio){
            Utils.showSnackWithViewMsgBgColor(mRootView, "Audio already uploading!", Snackbar.LENGTH_SHORT, Color.rgb(255,0,255));
            return;
        }

        mUploadingAudio = true;
        Utils.showSnackWithViewMsgBgColor(mRootView, "Recording finished! Transfering to server ...", Snackbar.LENGTH_SHORT, Color.YELLOW);
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mUploadingAudio = false;
                Utils.showSnackWithViewMsgBgColor(mRootView, "Audio Upload finished!", Snackbar.LENGTH_SHORT, Color.GREEN);
            }
        }.start();
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