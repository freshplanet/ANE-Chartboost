package com.freshplanet.ane.AirChartboost;

import android.view.View;

import com.chartboost.sdk.ChartBoostDelegate;

public class AirChartboostDelegate extends ChartBoostDelegate
{
	@Override
	public void didDismissInterstitial(View interstitialView)
	{
		AirChartboostExtension.context.dispatchStatusEventAsync("DidDismissInterstitial", "");
	}
	
	@Override
	public void didCloseInterstitial(View interstitialView)
	{
		AirChartboostExtension.context.dispatchStatusEventAsync("DidCloseInterstitial", "");
	}
	
	@Override
	public void didClickInterstitial(View interstitialView)
	{
		AirChartboostExtension.context.dispatchStatusEventAsync("DidClickInterstitial", "");
	}
	
	@Override
	public void didFailToLoadInterstitial()
	{
		AirChartboostExtension.context.dispatchStatusEventAsync("DidFailToLoadInterstitial", "");
	}
}
