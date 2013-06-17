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

#import "FlashRuntimeExtensions.h"
#import "Chartboost.h"

@interface AirChartboost : NSObject <ChartboostDelegate>

+ (AirChartboost *)sharedInstance;

@end


// C interface

DEFINE_ANE_FUNCTION(CBStartSession);
DEFINE_ANE_FUNCTION(CBShowInterstitial);
DEFINE_ANE_FUNCTION(CBCacheInterstitial);
DEFINE_ANE_FUNCTION(CBHasCachedInterstitial);


// ANE setup

/* AirChartboostExtInitializer()
 * The extension initializer is called the first time the ActionScript side of the extension
 * calls ExtensionContext.createExtensionContext() for any context.
 *
 * Please note: this should be same as the <initializer> specified in the extension.xml 
*/
void AirChartboostExtInitializer(void** extDataToSet, FREContextInitializer* ctxInitializerToSet, FREContextFinalizer* ctxFinalizerToSet);

/* AirChartboostExtFinalizer()
 * The extension finalizer is called when the runtime unloads the extension. However, it may not always called.
 *
 * Please note: this should be same as the <finalizer> specified in the extension.xml 
*/
void AirChartboostExtFinalizer(void* extData);

/* AirChartboostContextInitializer()
 * The context initializer is called when the runtime creates the extension context instance.
*/
void AirChartboostContextInitializer(void* extData, const uint8_t* ctxType, FREContext ctx, uint32_t* numFunctionsToTest, const FRENamedFunction** functionsToSet);

/* AirChartboostContextFinalizer()
 * The context finalizer is called when the extension's ActionScript code
 * calls the ExtensionContext instance's dispose() method.
 * If the AIR runtime garbage collector disposes of the ExtensionContext instance, the runtime also calls AirChartboostContextFinalizer().
*/
void AirChartboostContextFinalizer(FREContext ctx);


