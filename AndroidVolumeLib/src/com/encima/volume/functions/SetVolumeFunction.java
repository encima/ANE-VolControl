package com.encima.volume.functions;

import android.content.Context;
import android.media.AudioManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class SetVolumeFunction implements FREFunction {
	public static final String TAG = "SetVolumeFunction";

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Context appContext = context.getActivity().getApplicationContext();
		AudioManager am = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
		
		double vol = 1;
		try {
			vol = args[0].getAsDouble();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		int maxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
		vol = vol * maxVol;
		int index = (int) vol;
		
		am.setStreamVolume(am.STREAM_MUSIC, index, 0);
		return null;
	}
}
