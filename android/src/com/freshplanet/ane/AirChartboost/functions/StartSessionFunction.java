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

package com.freshplanet.ane.AirChartboost.functions;

import android.util.Log;
import android.view.View;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.chartboost.sdk.ChartBoost;
import com.chartboost.sdk.ChartBoostDelegate;
import com.freshplanet.ane.AirChartboost.AirChartboostDelegate;
import com.freshplanet.ane.AirChartboost.AirChartboostExtension;

/**
 * Example function
 */
public class StartSessionFunction implements FREFunction 
{
	private static String TAG = "AirChartboost";
	
	public FREObject call(FREContext context, FREObject[] args) 
	{
		// Retrieve the app ID
		String appId = null;
		try
		{
			appId = args[0].getAsString();
		}
		catch (Exception exception)
		{
			Log.d(TAG, exception.getLocalizedMessage());
			return null;
		}
		
		// Retrieve the app signature
		String appSignature = null;
		try
		{
			appSignature = args[1].getAsString();
		}
		catch (Exception exception)
		{
			Log.d(TAG, exception.getLocalizedMessage());
			return null;
		}
		
		ChartBoost chartboost = ChartBoost.getSharedChartBoost(context.getActivity());
		
		/*ChartBoostDelegate delegate = new ChartBoostDelegate() {
			@Override
			public void didCloseInterstitial(View interstitialView)
			{
				Log.d(TAG, "Close Interstitial");
				try
				{
					AirChartboostExtension.context.dispatchStatusEventAsync("DidCloseInterstitial", "");
				}
				catch (Exception exception)
				{
					Log.d(TAG, "Error: " + exception.getLocalizedMessage());
				}
			}
			
			@Override
			public void didFailToLoadInterstitial()
			{
				Log.d(TAG, "Fail to load Interstitial");
				try
				{
					AirChartboostExtension.context.dispatchStatusEventAsync("DidFailToLoadInterstitial", "");
				}
				catch (Exception exception)
				{
					Log.d(TAG, "Error: " + exception.getLocalizedMessage());
					exception.printStackTrace();
				}
			}
		};*/
		chartboost.setDelegate(new AirChartboostDelegate());
		
		chartboost.setAppId(appId);
		chartboost.setAppSignature(appSignature);
		chartboost.install();
		
		return null;
	}
}
