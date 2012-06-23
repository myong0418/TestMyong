package com.smartschool.tenversion;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingActivity extends Activity implements OnClickListener{
	private static final String TAG ="SettingActivity";
	/**  UI  **/
	//String

	//set Button
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
		/**  UI  **/
		//wifi layout
        LinearLayout wifiLayout =(LinearLayout)findViewById(R.id.wifi_layout);
//        wifiLayout.setOnClickListener(this);
        
        TextView wifiTV =(TextView)findViewById(R.id.wifi_tv);
        Spinner wifiSpinner= (Spinner)findViewById(R.id.wifi_spinner);
        
        
        //alram layout
        LinearLayout alramLayout =(LinearLayout)findViewById(R.id.alram_layout);
        alramLayout.setOnClickListener(this);
        
        TextView alramTV =(TextView)findViewById(R.id.alram_tv);
        
        //sound layout
        LinearLayout soundLayout =(LinearLayout)findViewById(R.id.sound_layout);
        soundLayout.setOnClickListener(this);
        
        TextView soundTV =(TextView)findViewById(R.id.sound_tv);
        ToggleButton soundTB= (ToggleButton)findViewById(R.id.sound_togglebtn);
        soundTB.setOnClickListener(this);
        
        //vibrate layout
        LinearLayout vibrateLayout =(LinearLayout)findViewById(R.id.vibrate_layout);
        vibrateLayout.setOnClickListener(this);
        
        TextView vibrateTV =(TextView)findViewById(R.id.vibrate_tv);
        ToggleButton vibrateTB= (ToggleButton)findViewById(R.id.vibrate_togglebtn);
        vibrateTB.setOnClickListener(this);
        
        //help layout
        LinearLayout helpLayout =(LinearLayout)findViewById(R.id.help_layout);
        helpLayout.setOnClickListener(this);
        
        TextView helpTV =(TextView)findViewById(R.id.help_tv);
        
        //version layout
        LinearLayout versionLayout =(LinearLayout)findViewById(R.id.version_layout);
        versionLayout.setOnClickListener(this);
        
        TextView versionTV =(TextView)findViewById(R.id.version_tv);
    }

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.alram_layout:
			Log.v(TAG,"onclick() alram_layout ");
			break;
		case R.id.sound_layout:
			Log.v(TAG,"onclick() sound_layout ");

			break;
		case R.id.vibrate_layout:
			Log.v(TAG,"onclick() vibrate_layout ");

			break;
		case R.id.help_layout:
			Log.v(TAG,"onclick() help_layout ");

			break;
		case R.id.version_layout:
			Log.v(TAG,"onclick() version_layout ");

			break;
		case R.id.sound_togglebtn:
			Log.v(TAG,"onclick() sound_togglebtn ");
			break;
		case R.id.vibrate_togglebtn:
			Log.v(TAG,"onclick() vibrate_togglebtn ");
			break;
//		case R.id.addBtn:
//
//			break;
//		case R.id.addBtn:
//
//			break;
//		case R.id.addBtn:
//
//			break;
//		case R.id.addBtn:
//
//			break;
//		case R.id.addBtn:
//
//			break;
//		case R.id.addBtn:
//
//			break;
//		case R.id.addBtn:
//
//			break;
//		case R.id.addBtn:
//
//			break;
	
		}
		
	}
}
