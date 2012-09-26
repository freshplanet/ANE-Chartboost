package com.freshplanet.ane
{
	import flash.events.Event;
	
	/**
	 * AirChartboostEvent replicates Chartboost SDK delegate protocol.<br><br>
	 * 
	 * Notes that Chartboost "More Apps" feature is not implemented yet.
	 * 
	 * @see #DID_DISMISS_INTERSTITIAL
	 * @see #DID_CLOSE_INTERSTITIAL
	 * @see #DID_CLICK_INTERSTITIAL
	 * @see #DID_FAIL_TO_LOAD_INTERSTITIAL
	 * @see #DID_CACHE_INTERSTITIAL
	 */
	public class AirChartboostEvent extends Event
	{
		/**
		 * Called when the user dismiss the interstitial, no matter how.
		 * 
		 * @see #DID_CLOSE_INTERSTITIAL
		 * @see #DID_CLICK_INTERSTITIAL
		 */
		public static const DID_DISMISS_INTERSTITIAL:String = "DidDismissInterstitial";
		
		/**
		 * Called when the user dismiss the interstitial by closing it.
		 * 
		 * @see #DID_DISMISS_INTERSTITIAL
		 * @see #DID_CLICK_INTERSTITIAL
		 */
		public static const DID_CLOSE_INTERSTITIAL:String = "DidCloseInterstitial";
		
		/**
		 * Called when the user dismiss the interstitial by clicking on the ad.
		 * 
		 * @see #DID_DISMISS_INTERSTITIAL
		 * @see #DID_CLOSE_INTERSTITIAL
		 */
		public static const DID_CLICK_INTERSTITIAL:String = "DidClickInterstitial";
		
		/**
		 * Called when an interstitial has failed to come back from the server.
		 * This may be due to network connection or that no interstitial is available for that user.
		 */
		public static const DID_FAIL_TO_LOAD_INTERSTITIAL:String = "DidFailToLoadInterstitial";
		
		/**
		 * Called when an interstitial has been received and cached.<br><br>
		 * 
		 * Note that this event is not dispatched on Android.
		 */
		public static const DID_CACHE_INTERSTITIAL:String = "DidCacheInterstitial";
		
		/**
		 * Name of the location related to this event.<br><br>
		 * 
		 * Note that this property is never set on Android.
		 */
		public var location:String;
		
		public function AirChartboostEvent(type:String, location:String=null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.location = location;
		}
	}
}