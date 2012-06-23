package com.smartschool.tenversion;

public class WifiProfile {
	long mId;
	String mMode=null;
	String mContents=null;
	boolean mCheckedWifi= false;

	public WifiProfile(String mMode, String mContents) {
		this.mMode = mMode;
		this.mContents = mContents;
	}

	public WifiProfile(long mId, String mode, String contents) {
		this.mId = mId;
		this.mMode = mode;
		this.mContents = contents;
	}

	public long getId() {
		return mId;
	}

	public String getMode() {
		return mMode;
	}

	public String getContents() {
		return mContents;
	}
}
