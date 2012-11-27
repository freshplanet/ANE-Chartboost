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

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.freshplanet.ane.AirChartboost.functions.CacheInterstitialFunction;
import com.freshplanet.ane.AirChartboost.functions.HasCachedInterstitialFunction;
import com.freshplanet.ane.AirChartboost.functions.ShowInterstitialFunction;
import com.freshplanet.ane.AirChartboost.functions.StartSessionFunction;

public class AirChartboostExtensionContext extends FREContext
{
	@Override
	public void dispose() 
	{
		AirChartboostExtension.context = null;
	}

	@Override
	public Map<String, FREFunction> getFunctions() 
	{
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
		
		functions.put("startSession", new StartSessionFunction());
		functions.put("showInterstitial", new ShowInterstitialFunction());
		functions.put("cacheInterstitial", new CacheInterstitialFunction());
		functions.put("hasCachedInterstitial", new HasCachedInterstitialFunction());
		
		return functions;	
	}
}
