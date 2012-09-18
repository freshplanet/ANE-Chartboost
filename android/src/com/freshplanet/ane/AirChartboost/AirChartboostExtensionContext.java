//////////////////////////////////////////////////////////////////////////////////////
//
//  Copyright 2012 Freshplanet (http://freshplanet.com | opensource@freshplanet.com)
//  
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//  
//    http://www.apache.org/licenses/LICENSE-2.0
//  
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//  
//////////////////////////////////////////////////////////////////////////////////////


package com.freshplanet.ane.AirChartboost;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.freshplanet.ane.AirChartboost.functions.CacheInterstitialFunction;
import com.freshplanet.ane.AirChartboost.functions.HasCachedInterstitialFunction;
import com.freshplanet.ane.AirChartboost.functions.ShowInterstitialFunction;
import com.freshplanet.ane.AirChartboost.functions.StartSessionFunction;

public class AirChartboostExtensionContext extends FREContext
{
	private static String TAG = "[AirChartboost]";
	
	public AirChartboostExtensionContext()
	{
		Log.d(TAG, "Creating Extension Context");
	}
	
	@Override
	public void dispose() 
	{
		Log.d(TAG, "Disposing Extension Context");
		AirChartboostExtension.context = null;
	}

	/**
	 * Registers AS function name to Java Function Class
	 */
	@Override
	public Map<String, FREFunction> getFunctions() 
	{
		Log.d(TAG, "Registering Extension Functions");
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		functionMap.put("startSession", new StartSessionFunction());
		functionMap.put("showInterstitial", new ShowInterstitialFunction());
		functionMap.put("cacheInterstitial", new CacheInterstitialFunction());
		functionMap.put("hasCachedInterstitial", new HasCachedInterstitialFunction());
		return functionMap;	
	}
}
