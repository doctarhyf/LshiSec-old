package com.doctarhyf.myapplication;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AnimationUtils;

//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import com.koziengineering.docta.pcone.R;

public class Utils {

    public static final String TAG = "CVD";
    public static final int SYMP_CHECK_PG_AGE_N_SEX = 0;
    public static final int SYMP_CHECK_PG_FEVER = 1;
    public static final int SYMP_CHECK_PG_CAUGH = 2;
    public static final int SYMP_CHECK_PG_DIFF_BREATHING = 3;
    public static final int SYMP_CHECK_PG_ADDITIONAL_QUESTIONS = 4;
    public static final int SYMP_CHECK_PG_RES = 5;
    public static final String TAG_PANEL_SLAVE = "main_panel_tag";
    public static final String KEY_LAST_DATA_COUNT = "kLastDataCount";
    public static final String TASKS_ORDER_KEY_NAME = "mTaskTimeStamp";
    public static final String STAYHOME_TASK_CLICK_ACTION_SHOW_ITEM_DETS = "showItemDetsAt";
    public static final String STAYHOME_TASK_CLICK_ACTION_DEL_ITEM = "delItemAt";
    public static final String APPSTORE_LINK = "https://app_store/app_link/taji.apk";
    public static final String PREF_KEY_FCM_TOKEN = "prefKeyFCMToken";

    private static final String NO_VALUE = "NO_VALUE";
    public static final String CVD_19_PREF_NAME = "CENI_PREF";
    public static final String VOTE_KEY_CAND_PREZ = "candPrez";
    private static final int NO_CANDIDATE = 0xbeef;
    public static final String VOTE_KEY_CAND_DEP_NAT = "candDepNat";
    public static final String VOTE_KEY_CAND_DEP_PROV = "candDepProv";
    public static final int REQ_CODE = 1001;
    public static final String PLAYSTORE_URL = "http://jtminvestment.com/ceni_beta.apk";

    public static final String KEY_SCREEN_DENSITY = "screenDensity";
    public static final String KEY_NEWS_ID = "newsId";
    public static final String KEY_NEWS_URL = "newsUrl";
    public static final String NO_PROMP_TEXT_DATA = "No promp data";
    private static final String NO_DATA = "no_data";
    public static String KeyTAJIAccountActive = "KeyTAJIAccountActive";

    /*public static  boolean IsConnected(Context applicationContext, boolean displayDialog){

        boolean connected = false;

        ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            connected = true;
        }

        return connected;
    }*/


    public static void Vibrate(Context context) {

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)); }
            else { v.vibrate(500); }

    }

    public static void PlayClickAnimation(Context context, View view, int animRessourceId) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_animation));
    }


}
