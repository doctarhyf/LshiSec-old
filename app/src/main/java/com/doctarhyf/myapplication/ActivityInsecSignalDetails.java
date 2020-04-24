package com.doctarhyf.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;

public class ActivityInsecSignalDetails extends AppCompatActivity {

    private static final String TAG = "TAG_INS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insec_item_details);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Intent intent = getIntent();

        if(intent != null){

            String jsonInsecSignal = intent.getStringExtra(InsecSignal.KEY_INSEC_SIGNAL);

            Log.e(TAG, "onCreate: insecSign : " + new Gson().fromJson(jsonInsecSignal, InsecSignal.class).toString());

        }else{
            Log.e(TAG, "onCreate: Intent is NULL!" );
        }
    }
}
