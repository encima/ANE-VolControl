package com.encima.volume;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.encima.volume.monitor.SettingsContentObserver;

public class VolumeExtension implements FREExtension {
	public static final String TAG = "VolumeExtension";

	public static FREContext extensionContext;
	public static Context appContext;
	public static SettingsContentObserver mSettingsWatcher;

	private static float NO_VALUE = (float)-1.0;
	private static Float lastSysVol = NO_VALUE;
	
	public static void notifyVolumeChange() {
		AudioManager am = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
		Float maxVol = Float.valueOf(am.getStreamMaxVolume(am.STREAM_MUSIC));
		Float sysVol = Float.valueOf(am.getStreamVolume(am.STREAM_MUSIC));
		
		if(sysVol != lastSysVol) {
			lastSysVol = sysVol;
			
			Float flashVol = sysVol / maxVol;
			
			Log.i(TAG, "system volume: " + sysVol);
			Log.i(TAG, "adjusted volume: " + flashVol);
			
			String vol = String.valueOf(flashVol);
			String eventName = "volumeChanged";
			
			extensionContext.dispatchStatusEventAsync(eventName, vol);
		}
	}
	
	@Override
	public FREContext createContext(String arg0) {
		return new VolumeExtensionContext();
	}

	@Override
	public void dispose() {
		Log.d(TAG, "Extension disposed.");

		// Stop watching settings for volume changes.
		VolumeExtension.appContext.getContentResolver().unregisterContentObserver(mSettingsWatcher);

		appContext = null;
		extensionContext = null;
		mSettingsWatcher = null;
		lastSysVol = NO_VALUE;
	}

	@Override
	public void initialize() {
		Log.d(TAG, "Extension initialized");
		
	}
	
}
