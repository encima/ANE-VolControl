package com.encima.volume.functions;

import android.content.Context;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.encima.volume.VolumeExtension;

public class InitFunction implements FREFunction {

	public static final String TAG = "InitFunction";
	
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		VolumeExtension.extensionContext = context;
		
		Context appContext = context.getActivity().getApplicationContext();
		VolumeExtension.appContext = appContext;
		return null;
	}

}
