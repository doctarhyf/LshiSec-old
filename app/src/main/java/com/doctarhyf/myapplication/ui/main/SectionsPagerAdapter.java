package com.doctarhyf.myapplication.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.doctarhyf.myapplication.R;
import com.doctarhyf.myapplication.frags.FragmentSignal;
import com.doctarhyf.myapplication.frags.FragmentInsecHistory;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_history, R.string.tab_text_signal,
            R.string.tab_text_map};
    public static final int FRAG_HISTORY = 0;
    public static final int FRAG_SIGNAL_INSEC = 1;
    public static final int FRAG_MAP = 2;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = PlaceholderFragment.newInstance(position + 1);

        switch (position){
            case FRAG_HISTORY:
                fragment = FragmentInsecHistory.newInstance(1);
                break;

            case FRAG_SIGNAL_INSEC:
                fragment = FragmentSignal.newInstance("","");
                break;

            case FRAG_MAP:
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_TITLES.length;
    }
}