package com.encima.volume.monitor;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

import com.encima.volume.VolumeExtension;

public class SettingsContentObserver extends ContentObserver {

	public static final String TAG = "SettingsContentObserver";
	
	public SettingsContentObserver(Handler handler) {
		super(handler);
	}

	@Override
	public boolean deliverSelfNotifications() {
	     return super.deliverSelfNotifications(); 
	}

	@Override
	public void onChange(boolean selfChange) {
	    super.onChange(selfChange);
	    Log.v(TAG, "Settings change detected");

		// Dispatch event to AIR.
		VolumeExtension.notifyVolumeChange();
	}
}
