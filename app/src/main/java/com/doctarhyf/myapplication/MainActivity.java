package com.doctarhyf.myapplication;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.doctarhyf.myapplication.frags.FragmentSignal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.doctarhyf.myapplication.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements FragmentSignal.OnFragmentInteractionListener {

    private static final String TAG = "TAG";
    private TabLayout mTabs = null;

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
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.my_statusbar_color));

        }

        showTab(SectionsPagerAdapter.FRAG_SIGNAL_INSEC);
    }

    public void showTab(int tabIdx){
        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        mTabs.getTabAt(tabIdx).select();
                    }
                }, 100);
    }

    @Override
    public void onSignalInsecClicked(Uri uri) {
        Log.e(TAG, "onSignalInsecClicked: " );
    }

    @Override
    public void onShowTabClicked(int fragIdx) {
        showTab(fragIdx);
    }
}