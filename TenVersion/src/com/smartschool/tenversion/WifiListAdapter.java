package com.smartschool.tenversion;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WifiListAdapter extends ArrayAdapter<CheckListProfile> implements OnClickListener ,OnLongClickListener{
	private static final String TAG = "CheckListAdapter";
	private final LayoutInflater mInflater;
	// private static int MODE; //safe,live,etc
	private boolean DEL_MODE = false;
	private boolean ALL_DEL_MODE = false;
	private ArrayList<WifiProfile> wifiInfoList;
	private Context mContext;
	private ViewHolder holder;
	
	private  ArrayList<WifiProfile> delChecListProfileList;
	// List<ContacstData> contacts;
	public WifiListAdapter(Context context, int textViewResourceId, ArrayList<WifiProfile> objects, boolean del_mode) {
		super(context, textViewResourceId);
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		wifiInfoList = objects;

		DEL_MODE = del_mode;
	}
	
	public WifiListAdapter(Context context, int textViewResourceId,ArrayList<WifiProfile> objects, boolean del_mode,boolean all_del_mode) {
		super(context, textViewResourceId);
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		wifiInfoList = objects;
		delChecListProfileList =  new ArrayList<WifiProfile>();
		DEL_MODE = del_mode;
		ALL_DEL_MODE = all_del_mode;
		if(ALL_DEL_MODE){
			delChecListProfileList = objects;
		}
	}

	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Log.v(TAG, "getView");
		// final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.checklist_item_row, null);
			holder = new ViewHolder();
			holder.mRLayout = (RelativeLayout) convertView.findViewById(R.id.buddy_rayout);
			// holder.mImage
			// =(ImageView)convertView.findViewById(R.id.buddy_image);
			// holder.mMode = (TextView) convertView.findViewById(R.id.mode);
			holder.mContents = (TextView) convertView.findViewById(R.id.contents);
			holder.mDelBtn = (ImageButton) convertView.findViewById(R.id.mindelBtn);
						
			if (DEL_MODE) {
				holder.mDelBtn.setVisibility(View.VISIBLE);
			} else {
				holder.mDelBtn.setVisibility(View.GONE);
			}
			
			if (ALL_DEL_MODE) { //all Select
				holder.mDelBtn.setBackgroundResource(R.drawable.check2);
				holder.mDelBtn.setTag(R.id.tag_del_satate, true);
			} else { //all deSelect
				holder.mDelBtn.setBackgroundResource(R.drawable.check1);
				holder.mDelBtn.setTag(R.id.tag_del_satate, false);
			}

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// set background
		if (position % 2 == 0) {
			holder.mRLayout.setBackgroundResource(R.drawable.panal_2);
		} else {
			holder.mRLayout.setBackgroundResource(R.drawable.panal_1);
		}

		WifiProfile item = wifiInfoList.get(position);
		holder.mContents.setText(item.getContents());

		holder.mDelBtn.setTag(R.id.tag_id, item.getId());
		holder.mDelBtn.setTag(R.id.tag_mode, item.getMode());
		holder.mDelBtn.setTag(R.id.tag_contents, item.getContents());
		holder.mDelBtn.setOnClickListener(this);
		
		convertView.setTag(R.id.tag_id, item.getId());
		convertView.setTag(R.id.tag_mode, item.getMode());
		convertView.setTag(R.id.tag_contents, item.getContents());
		convertView.setOnLongClickListener(this);
		
		return convertView;
	}

	private class ViewHolder {
		private RelativeLayout mRLayout;
		private TextView mContents;
		private ImageButton mDelBtn;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mindelBtn:
			Log.v(TAG, "mDelBtn [onClick]");
			long id = Long.valueOf(v.getTag(R.id.tag_id).toString());
			Log.v(TAG, "id " + id);
			int mode = Integer.parseInt(v.getTag(R.id.tag_mode).toString());
			Log.v(TAG, "mode " + mode);
			String contents = v.getTag(R.id.tag_contents).toString();
			Log.v(TAG, "contents " + contents);
			
			
			boolean delState= (Boolean) v.getTag(R.id.tag_del_satate);
			
			if (delState) { //checked -> uncheck
				delState = false;
				v.setTag(R.id.tag_del_satate, false);
				v.setBackgroundResource(R.drawable.check1);
				for(int i=0; i<delChecListProfileList.size(); i++){
					if(delChecListProfileList.get(i).getId() == id){
						delChecListProfileList.remove(i);//(new CheckListProfile(id, String.valueOf(mode),contents));
					}
				}
			} else {  //unchecked -> check
				delState = true;
				v.setTag(R.id.tag_del_satate, true);
				v.setBackgroundResource(R.drawable.check2);
				delChecListProfileList.add(new WifiProfile(id, String.valueOf(mode),contents));
			}
			
			
//			if (mode == 1) { 				// safe
//				((SafeListActivity) mContext).delCheckListDialog(id, contents);
//
//			} else if (mode == 2) { 	// live
//				((LiveListActivity) mContext).delCheckListDialog(id, contents);
//				// }else if(MODE ==3){ //etc
//				// ((EtcListActivity)mContext).addCheckList(addText);
//			}
			break;
		}
	}	
	

	public boolean onLongClick(View v) {
		Log.v(TAG,"listLongClick()  ");
		long rowId = Long.valueOf(v.getTag(R.id.tag_id).toString());
		String contents = v.getTag(R.id.tag_contents).toString();
		int mode = Integer.parseInt(v.getTag(R.id.tag_mode).toString());
		
		if (mode == 1) { // safe
			((SafeListActivity) mContext).modifyCheckListDialog(rowId,contents);

		} else if (mode == 2) { // live
			((LiveListActivity) mContext).modifyCheckListDialog(rowId, contents);
 		}else if(mode ==3){ //etc
 			((EtcListActivity)mContext).modifyCheckListDialog(rowId,contents);
		}

		return false;
	}
	
	public  ArrayList<WifiProfile> getAllDelList (){
		Log.v(TAG,"getAllDelList()  ");
		
		return delChecListProfileList;
		    
	}


}