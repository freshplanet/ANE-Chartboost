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

package com.freshplanet.ane
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * AirChartboost replicates Chartboost SDK APIs.<br><br>
	 * 
	 * Note that this class is a singleton. Do not try to instanciate it, but call
	 * <code>getInstance()</code> instead.<br><br>
	 * 
	 * Chartboost SDK delegate protocol is replicated through a set of events
	 * described in the class <code>AirChartboostEvent</code>. These events
	 * are dispatched by the AirChartboost instance.<br><br>
	 * 
	 * Note that Chartboost "More Apps" feature is not implemented yet.
	 * 
	 * @see AirChartboostEvent
	 */
	public class AirChartboost extends EventDispatcher
	{
		private static var _instance:AirChartboost;

		private var extCtx:ExtensionContext = null;
		
		public function AirChartboost()
		{
			if (!_instance)
			{
				extCtx = ExtensionContext.createExtensionContext("com.freshplanet.AirChartboost", null);
				if (extCtx != null)
				{
					extCtx.addEventListener(StatusEvent.STATUS, onStatus);
				} 
				else
				{
					trace('[AirChartboost] Error - Extension Context is null.');
				}
				_instance = this;
			}
			else
			{
				throw Error( 'This is a singleton, use getInstance(), do not call the constructor directly.');
			}
		}
		
		public static function getInstance() : AirChartboost
		{
			return _instance ? _instance : new AirChartboost();
		}
		
		
		// --------------------------------------------------------------------------------------//
		//																						 //
		// 							   			PUBLIC API										 //
		// 																						 //
		// --------------------------------------------------------------------------------------//
		
		/**
		 * Notify the beginning of a user session
		 * 
		 * @param appID String: Your Chartboost application ID.
		 * @param appSignature String: Your Chartboost application signature.
		 */
		public function startSession( appID : String, appSignature : String ) : void
		{
			extCtx.call('startSession', appID, appSignature);
		}
		
		/**
		 * Show an interstitial, optionally with a location identifier.
		 * 
		 * @param location String (optional): A Chartboost location identifier.
		 */
		public function showInterstitial( location : String = null ) : void
		{
			if (location) extCtx.call('showInterstitial', location);
			else extCtx.call('showInterstitial');
		}
		
		/**
		 * Start caching an interstitial, optionally with a location identifier.
		 * 
		 * @param location String (optional): A Chartboost location identifier.
		 */
		public function cacheInterstitial( location : String = null ) : void
		{
			if (location) extCtx.call('cacheInterstitial', location);
			else extCtx.call('cacheInterstitial');
		}
		
		/**
		 * Check if an interstitial is cached, optionally with a location identifier.
		 * 
		 * @param location String (optional): A Chartboost location identifier.
		 */
		public function hasCachedInterstitial( location : String = null ) : Boolean
		{
			if (location) return extCtx.call('hasCachedInterstitial', location);
			else return extCtx.call('hasCachedInterstitial');
		}
		
		
		// --------------------------------------------------------------------------------------//
		//																						 //
		// 							   		EVENT LISTENERS										 //
		// 																						 //
		// --------------------------------------------------------------------------------------//
		
		private function onStatus( event : StatusEvent ) : void
		{
			var e:AirChartboostEvent;
			
			switch (event.code)
			{
				case AirChartboostEvent.DID_DISMISS_INTERSTITIAL:
					e = new AirChartboostEvent(AirChartboostEvent.DID_DISMISS_INTERSTITIAL, event.level);
					break;
				
				case AirChartboostEvent.DID_CLOSE_INTERSTITIAL:
					e = new AirChartboostEvent(AirChartboostEvent.DID_CLOSE_INTERSTITIAL, event.level);
					break;
				
				case AirChartboostEvent.DID_CLICK_INTERSTITIAL:
					e = new AirChartboostEvent(AirChartboostEvent.DID_CLICK_INTERSTITIAL, event.level);
					break;
				
				case AirChartboostEvent.DID_FAIL_TO_LOAD_INTERSTITIAL:
					e = new AirChartboostEvent(AirChartboostEvent.DID_FAIL_TO_LOAD_INTERSTITIAL, event.level);
					break;
				
				case AirChartboostEvent.DID_CACHE_INTERSTITIAL:
					e = new AirChartboostEvent(AirChartboostEvent.DID_CACHE_INTERSTITIAL, event.level);
					break;
				
				case "LOGGING":
					trace('[AirChartboost] ' + event.level);
			}
			
			if (e) dispatchEvent(e);
		}
	}
}
