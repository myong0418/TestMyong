package com.smartschool.tenversion;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
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
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingActivity extends Activity implements OnClickListener{
	private static final String TAG ="SettingActivity";
	/**  UI  **/
	//String
	public static String not_wifi_mode_str;
	//set Button
	public static LinearLayout wifiLayout = null;
	public static Button wifiBtn = null;	
	public static TextView wifiTV = null;
	public static TextView wifiSummaryTV = null;

	//set shared Preference
	public static final String KEY_WIFI_MODE = "wifi_mode";
	public static final String KEY_WIFISSID = "wifi_ssid";
	public static final String KEY_WIFIBSSID = "wifi_bssid";

	public static final String KEY_ALARM = "alarm_mode";
	public static final String KEY_VIBRATOR = "vibrator_mode";
	
	//dialog
    private CustomDialog settingDialog = null ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
		/**  UI  **/
		//wifi layout
       wifiLayout = (LinearLayout)findViewById(R.id.wifi_layout);
//        wifiLayout.setOnClickListener(this);
        
        wifiTV = (TextView)findViewById(R.id.wifi_tv);
        wifiSummaryTV = (TextView)findViewById(R.id.wifi_summary_tv);
        wifiBtn= (Button)findViewById(R.id.wifi_btn);
        wifiBtn.setOnClickListener(this);
               
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
    
	@Override
	protected void onResume() {
		super.onResume();
		Log.v(TAG, "onResume()");

		initWifiSetting();
	}

	public void onClick(View v) {
		switch(v.getId()){
		
		
		case R.id.wifi_btn:
			Log.v(TAG,"onclick() wifi_btn ");
			chooseWifiDialog();
			
			break;
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
	
	
//Wifi START
	public void initWifiSetting() {
		Log.v(TAG, "initWifiSetting()");

		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		List<ScanResult> mScanResult = wifiManager.getScanResults(); // ScanResult
		if (wifiManager == null || mScanResult == null) {
			Toast.makeText(this, "연결할 Wi-Fi가 없습니다.", Toast.LENGTH_LONG).show();
			wifiLayout.setEnabled(false);
			for (int i = 0; i < wifiLayout.getChildCount(); i++) {
				View view = wifiLayout.getChildAt(i);
				view.setEnabled(false);  //child view
			}
		} else {
			wifiLayout.setEnabled(true);
			for (int i = 0; i < wifiLayout.getChildCount(); i++) {
				View view = wifiLayout.getChildAt(i);
				view.setEnabled(true); //child view
			}
		}

        String wifiSSID = WifiReceiver.getWifiSettingSSID(this);
        wifiSummaryTV.setText(wifiSSID);
//        String wifiBSSID = WifiReceiver.getWifiSettingBSSID(this);
//        wifiSummaryTV.setText(wifiBSSID);	
	}
	
	public static ArrayList<WifiListProfile> getWifiScanList(Context context) {
		Log.v(TAG, "getWifiScanList()");

		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		// init WIFISCAN
		List<ScanResult> mScanResult = wifiManager.getScanResults(); // ScanResult
			
		ArrayList<WifiListProfile> wifiListItem = new ArrayList<WifiListProfile>(); 
		wifiListItem.add(new WifiListProfile("설정안함", "",""));
		
		if (wifiManager != null && mScanResult != null) {
			Log.v(TAG, "=======================================\n");
			for (int i = 0; i < mScanResult.size(); i++) {
				ScanResult result = mScanResult.get(i);
//				Log.v(TAG, (i + 1) + ". SSID : " + result.SSID.toString()+ "\t\t RSSI : " + result.level + " dBm\n");			
//				Log.v(TAG, "frequency : "+result.frequency);
//				Log.v(TAG, "capabilities : "+result.capabilities);
//				Log.v(TAG, "describeContents : "+result.describeContents());
				String ssid  = result.SSID.toString();
				String bssid = result.BSSID.toString();
				String rssi = String.valueOf(result.level); 
				
				wifiListItem.add(new WifiListProfile(ssid, bssid, rssi));
			}
			Log.v(TAG, "=======================================\n");
		}
		return wifiListItem;
	}
	
	/**Custom dialog START**/    
    public void chooseWifiDialog(){ 
    	Log.v(TAG,"[chooseWifiDialog]");
    	CustomDialog customDialog =  new CustomDialog(this, R.style.Dialog);   
    	customDialog.chooseWifiDialog(getWifiScanList(this));
    	customDialog.show();
    }
    public  void  chooseWifi(WifiListProfile item){
	   	 Log.v(TAG,"[chooseWifi] ");
		String ssid = item.getSSID();
		String bssid = item.getBSSID();
//		String rssi = item.getRSSI();
		
		wifiSummaryTV.setText(ssid);
		setWifiSetting(ssid,bssid);
		
		WifiReceiver.compairWifiConnectionMode(this);
		
  }

	// set wifi sharedPreference
	public void setWifiSetting(String wifiSSID, String wifiBSSID) {
		SharedPreferences sharedPrefs = this.getSharedPreferences(KEY_WIFI_MODE, Context.MODE_PRIVATE);
		Editor editor = sharedPrefs.edit();

		editor.putString(KEY_WIFISSID, wifiSSID); // SSID
		editor.putString(KEY_WIFIBSSID, wifiBSSID); // BSSID

		editor.commit();
	}
	
//Wifi END
	
	
//Alram START	
	
	
//Alram END
	

//	// set checkBoxPreference Summary
//	public void setCheckBoxSummary(Preference pref, String hwMode,
//			String checkState) {
//		if (checkState.equals("true")) {
//			checkStateStr = " on";
//		} else {
//			checkStateStr = " off";
//		}
//		pref.setSummary(hwMode + checkStateStr);
//	}
	
}
