package com.krish512.k_exchange.Utils;

import android.content.Context;

import com.google.android.gcm.GCMBroadcastReceiver;

public class App42GCMReceiver extends GCMBroadcastReceiver{
	@Override
	protected String getGCMIntentServiceClassName(Context context) { 
		return "com.krish512.k_exchange.Utils.App42GCMService"; 
	} 
}