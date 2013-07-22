package com.encima.volume;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.encima.volume.functions.InitFunction;
import com.encima.volume.functions.SetVolumeFunction;

public class VolumeExtensionContext extends FREContext {
	public static final String TAG = "VolumeExtensionContext";
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Log.d(TAG,"Context disposed.");
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();

		functions.put("init", new InitFunction());
		functions.put("setVolume", new SetVolumeFunction());

		return functions;
	}

}
