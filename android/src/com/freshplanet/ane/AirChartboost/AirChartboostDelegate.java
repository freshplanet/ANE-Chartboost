package com.freshplanet.ane.AirChartboost;

import com.chartboost.sdk.ChartboostDelegate;

public class AirChartboostDelegate implements ChartboostDelegate
{
	/* 
	 * shouldDisplayInterstitial(String location)
	 *
	 * This is used to control when an interstitial should or should not be displayed
	 * If you should not display an interstitial, return NO
	 *
	 * For example: during gameplay, return NO.
	 *
	 * Is fired on:
	 * - showInterstitial()
	 * - Interstitial is loaded & ready to display
	 */
	@Override
	public boolean shouldDisplayInterstitial(String location)
	{
		AirChartboostExtension.log("Should display interstitial for location \""+location+"\"? Yes.");
		return true;
	}
	
	/*
	 * shouldRequestInterstitial(String location)
	 * 
	 * This is used to control when an interstitial should or should not be requested
	 * If you should not request an interstitial from the server, return NO
	 *
	 * For example: user should not see interstitials for some reason, return NO.
	 *
	 * Is fired on:
	 * - cacheInterstitial()
	 * - showInterstitial() if no interstitial is cached
	 * 
	 * Notes: 
	 * - We do not recommend excluding purchasers with this delegate method
	 * - Instead, use an exclusion list on your campaign so you can control it on the fly
	 */
	@Override
	public boolean shouldRequestInterstitial(String location)
	{
		AirChartboostExtension.log("Should request interstitial for location \""+location+"\"? Yes.");
		return true;
	}
	
	/*
	 * didCacheInterstitial(String location)
	 * 
	 * Passes in the location name that has successfully been cached
	 * 
	 * Is fired on:
	 * - cacheInterstitial() success
	 * - All assets are loaded
	 * 
	 * Notes:
	 * - Similar to this is: cb.hasCachedInterstitial(String location) 
	 * Which will return true if a cached interstitial exists for that location
	 */
	@Override
	public void didCacheInterstitial(String location)
	{
		AirChartboostExtension.log("Did cache interstitial for location \""+location+"\".");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidCacheInterstitial", location);
	}

	/*
	 * didFailToLoadInterstitial(String location)
	 * 
	 * This is called when an interstitial has failed to load for any reason
	 * 
	 * Is fired on:
	 * - cacheInterstitial() failure
	 * - showInterstitial() failure if no interstitial was cached
	 * 
	 * Possible reasons:
	 * - No network connection
	 * - No publishing campaign matches for this user (go make a new one in the dashboard)
	 */
	@Override
	public void didFailToLoadInterstitial(String location)
	{
	    AirChartboostExtension.log("Did fail to load interstitial for location \""+location+"\".");
	    AirChartboostExtension.context.dispatchStatusEventAsync("DidFailToLoadInterstitial", location);
	}

	/*
	 * didDismissInterstitial(String location)
	 *
	 * This is called when an interstitial is dismissed
	 *
	 * Is fired on:
	 * - Interstitial click
	 * - Interstitial close
	 *
	 * #Pro Tip: Use the delegate method below to immediately re-cache interstitials
	 */
	@Override
	public void didDismissInterstitial(String location)
	{
		AirChartboostExtension.log("Did dismiss interstitial for location \""+location+"\".");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidDismissInterstitial", location);
	}

	/*
	 * didCloseInterstitial(String location)
	 *
	 * This is called when an interstitial is closed
	 *
	 * Is fired on:
	 * - Interstitial close
	 */
	@Override
	public void didCloseInterstitial(String location)
	{
		AirChartboostExtension.log("Did close interstitial for location \""+location+"\".");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidCloseInterstitial", location);
	}

	/*
	 * didClickInterstitial(String location)
	 *
	 * This is called when an interstitial is clicked
	 *
	 * Is fired on:
	 * - Interstitial click
	 */
	@Override
	public void didClickInterstitial(String location)
	{
		AirChartboostExtension.log("Did click interstitial for location \""+location+"\".");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidClickInterstitial", location);
	}

	/*
	 * didShowInterstitial(String location)
	 *
	 * This is called when an interstitial has been successfully shown
	 *
	 * Is fired on:
	 * - showInterstitial() success
	 */
	@Override
	public void didShowInterstitial(String location)
	{
		AirChartboostExtension.log("Did show interstitial for location \""+location+"\".");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidShowInterstitial", location);
	}

	/*
	 * More Apps delegate methods
	 */
	
	/*
	 * shouldDisplayLoadingViewForMoreApps()
	 *
	 * Return NO to prevent the pretty More-Apps loading screen
	 *
	 * Is fired on:
	 * - showMoreApps()
	 */
	@Override
	public boolean shouldDisplayLoadingViewForMoreApps()
	{
		AirChartboostExtension.log("Should display loading view for More Apps? Yes.");
		return true;
	}

	/*
	 * shouldRequestMoreApps()
	 * 
	 * Return NO to prevent a More-Apps page request
	 *
	 * Is fired on:
	 * - cacheMoreApps()
	 * - showMoreApps() if no More-Apps page is cached
	 */
	@Override
	public boolean shouldRequestMoreApps()
	{
		AirChartboostExtension.log("Should request More Apps? Yes.");
		return true;
	}

	/*
	 * shouldDisplayMoreApps()
	 * 
	 * Return NO to prevent the More-Apps page from displaying
	 *
	 * Is fired on:
	 * - showMoreApps() 
	 * - More-Apps page is loaded & ready to display
	 */
	@Override
	public boolean shouldDisplayMoreApps()
	{
		AirChartboostExtension.log("Should display More Apps? Yes.");
		return true;
	}

	/*
	 * didFailToLoadMoreApps()
	 * 
	 * This is called when the More-Apps page has failed to load for any reason
	 * 
	 * Is fired on:
	 * - cacheMoreApps() failure
	 * - showMoreApps() failure if no More-Apps page was cached
	 * 
	 * Possible reasons:
	 * - No network connection
	 * - No publishing campaign matches for this user (go make a new one in the dashboard)
	 */
	@Override
	public void didFailToLoadMoreApps()
	{
		AirChartboostExtension.log("Did fail to load More Apps.");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidFailToLoadMoreApps", "");
	}

	/*
	 * didCacheMoreApps()
	 * 
	 * Is fired on:
	 * - cacheMoreApps() success
	 * - All assets are loaded
	 */
	@Override
	public void didCacheMoreApps()
	{
		AirChartboostExtension.log("Did cache More Apps.");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidCacheMoreApps", "");
	}

	/*
	 * didDismissMoreApps()
	 *
	 * This is called when the More-Apps page is dismissed
	 *
	 * Is fired on:
	 * - More-Apps click
	 * - More-Apps close
	 */
	@Override
	public void didDismissMoreApps()
	{
		AirChartboostExtension.log("Did dismiss More Apps.");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidDismissMoreApps", "");
	}

	/*
	 * didCloseMoreApps()
	 *
	 * This is called when the More-Apps page is closed
	 *
	 * Is fired on:
	 * - More-Apps close
	 */
	@Override
	public void didCloseMoreApps()
	{
		AirChartboostExtension.log("Did close More Apps.");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidCloseMoreApps", "");
	}

	/*
	 * didClickMoreApps()
	 *
	 * This is called when the More-Apps page is clicked
	 *
	 * Is fired on:
	 * - More-Apps click
	 */
	@Override
	public void didClickMoreApps()
	{
		AirChartboostExtension.log("Did click More Apps.");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidClickMoreApps", "");
	}

	/*
	 * didShowMoreApps()
	 *
	 * This is called when the More-Apps page has been successfully shown
	 *
	 * Is fired on:
	 * - showMoreApps() success
	 */
	@Override
	public void didShowMoreApps()
	{
		AirChartboostExtension.log("Did show More Apps.");
		AirChartboostExtension.context.dispatchStatusEventAsync("DidShowMoreApps", "");
	}

	/*
	 * shouldRequestInterstitialsInFirstSession()
	 *
	 * Return false if the user should not request interstitials until the 2nd startSession()
	 * 
	 */
	@Override
	public boolean shouldRequestInterstitialsInFirstSession()
	{
		AirChartboostExtension.log("Should request interstitials in first session? Yes.");
		return true;
	}
}
