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

#import "AirChartboost.h"

FREContext AirChartboostCtx = nil;


@implementation AirChartboost

#pragma mark - Singleton

static AirChartboost *sharedInstance = nil;

+ (AirChartboost *)sharedInstance
{
    if (sharedInstance == nil)
    {
        sharedInstance = [[super allocWithZone:NULL] init];
    }

    return sharedInstance;
}

+ (id)allocWithZone:(NSZone *)zone
{
    return [self sharedInstance];
}

- (id)copy
{
    return self;
}


#pragma mark - ChartboostDelegate

- (void)didDismissInterstitial:(NSString *)location
{
    if (AirChartboostCtx != nil)
    {
        FREDispatchStatusEventAsync(AirChartboostCtx, (const uint8_t *)"DidDismissInterstitial", (const uint8_t *)[location UTF8String]);
    }
}

- (void)didCloseInterstitial:(NSString *)location
{
    if (AirChartboostCtx != nil)
    {
        FREDispatchStatusEventAsync(AirChartboostCtx, (const uint8_t *)"DidCloseInterstitial", (const uint8_t *)[location UTF8String]);
    }
}

- (void)didClickInterstitial:(NSString *)location
{
    if (AirChartboostCtx != nil)
    {
        FREDispatchStatusEventAsync(AirChartboostCtx, (const uint8_t *)"DidClickInterstitial", (const uint8_t *)[location UTF8String]);
    }
}

- (void)didFailToLoadInterstitial:(NSString *)location
{
    if (AirChartboostCtx != nil)
    {
        FREDispatchStatusEventAsync(AirChartboostCtx, (const uint8_t *)"DidFailToLoadInterstitial", (const uint8_t *)[location UTF8String]);
    }
}

- (void)didCacheInterstitial:(NSString *)location
{
    if (AirChartboostCtx != nil)
    {
        FREDispatchStatusEventAsync(AirChartboostCtx, (const uint8_t *)"DidCacheInterstitial", (const uint8_t *)[location UTF8String]);
    }
}

@end


#pragma mark - C interface

DEFINE_ANE_FUNCTION(CBStartSession)
{
    uint32_t stringLength;
    
    const uint8_t *valueAppId;
    if (FREGetObjectAsUTF8(argv[0], &stringLength, &valueAppId) != FRE_OK)
    {
        return nil;
    }
    NSString *appId = [NSString stringWithUTF8String:(char*)valueAppId];
    
    const uint8_t *valueAppSignature;
    if (FREGetObjectAsUTF8(argv[1], &stringLength, &valueAppSignature) != FRE_OK)
    {
        return nil;
    }
    NSString *appSignature = [NSString stringWithUTF8String:(char*)valueAppSignature];
    
    Chartboost *chartboost = [Chartboost sharedChartboost];
    chartboost.delegate = [AirChartboost sharedInstance];
    chartboost.appId = appId;
    chartboost.appSignature = appSignature;
    [chartboost startSession];
    
    return nil;
}

DEFINE_ANE_FUNCTION(CBShowInterstitial)
{
    if (argc > 0)
    {
        uint32_t stringLength;
        const uint8_t *valueLocation;
        if (FREGetObjectAsUTF8(argv[0], &stringLength, &valueLocation) != FRE_OK)
        {
            return nil;
        }
        NSString *location = [NSString stringWithUTF8String:(char*)valueLocation];
        [[Chartboost sharedChartboost] showInterstitial:location];
    }
    else
    {
        [[Chartboost sharedChartboost] showInterstitial];
    }
    
    return nil;
}

DEFINE_ANE_FUNCTION(CBCacheInterstitial)
{
    if (argc > 0)
    {
        uint32_t stringLength;
        const uint8_t *valueLocation;
        if (FREGetObjectAsUTF8(argv[0], &stringLength, &valueLocation) != FRE_OK)
        {
            return nil;
        }
        NSString *location = [NSString stringWithUTF8String:(char*)valueLocation];
        [[Chartboost sharedChartboost] cacheInterstitial:location];
    }
    else
    {
        [[Chartboost sharedChartboost] cacheInterstitial];
    }
    
    return nil;
}

DEFINE_ANE_FUNCTION(CBHasCachedInterstitial)
{
    BOOL hasCachedInterstitial;
    if (argc > 0)
    {
        uint32_t stringLength;
        const uint8_t *valueLocation;
        if (FREGetObjectAsUTF8(argv[0], &stringLength, &valueLocation) != FRE_OK)
        {
            return nil;
        }
        NSString *location = [NSString stringWithUTF8String:(char*)valueLocation];
        hasCachedInterstitial = [[Chartboost sharedChartboost] hasCachedInterstitial:location];
    }
    else
    {
        hasCachedInterstitial = [[Chartboost sharedChartboost] hasCachedInterstitial];
    }
    
    FREObject freObject;
    FREResult freResult = FRENewObjectFromBool(YES, &freObject);
    if (freResult != FRE_OK)
    {
        NSLog(@"[AirChartboost] FREResult = %d", freResult);
    }
    
    return freObject;
}


#pragma mark - ANE setup

/* AirChartboostExtInitializer()
 * The extension initializer is called the first time the ActionScript side of the extension
 * calls ExtensionContext.createExtensionContext() for any context.
 *
 * Please note: this should be same as the <initializer> specified in the extension.xml 
 */
void AirChartboostExtInitializer(void** extDataToSet, FREContextInitializer* ctxInitializerToSet, FREContextFinalizer* ctxFinalizerToSet) 
{
    NSLog(@"Entering AirChartboostExtInitializer()");

    *extDataToSet = NULL;
    *ctxInitializerToSet = &ContextInitializer;
    *ctxFinalizerToSet = &ContextFinalizer;

    NSLog(@"Exiting AirChartboostExtInitializer()");
}

/* AirChartboostExtFinalizer()
 * The extension finalizer is called when the runtime unloads the extension. However, it may not always called.
 *
 * Please note: this should be same as the <finalizer> specified in the extension.xml 
 */
void AirChartboostExtFinalizer(void* extData) 
{
    NSLog(@"Entering AirChartboostExtFinalizer()");

    // Nothing to clean up.
    NSLog(@"Exiting AirChartboostExtFinalizer()");
    return;
}

/* ContextInitializer()
 * The context initializer is called when the runtime creates the extension context instance.
 */
void ContextInitializer(void* extData, const uint8_t* ctxType, FREContext ctx, uint32_t* numFunctionsToTest, const FRENamedFunction** functionsToSet)
{
    NSLog(@"Entering ContextInitializer()");

    /* The following code describes the functions that are exposed by this native extension to the ActionScript code.
     * As a sample, the function isSupported is being provided.
     */
    *numFunctionsToTest = 4;

    FRENamedFunction* func = (FRENamedFunction*) malloc(sizeof(FRENamedFunction) * (*numFunctionsToTest));
    
    func[0].name = (const uint8_t*) "startSession";
    func[0].functionData = NULL;
    func[0].function = &CBStartSession;
    
    func[1].name = (const uint8_t*) "showInterstitial";
    func[1].functionData = NULL;
    func[1].function = &CBShowInterstitial;
    
    func[2].name = (const uint8_t*) "cacheInterstitial";
    func[2].functionData = NULL;
    func[2].function = &CBCacheInterstitial;
    
    func[3].name = (const uint8_t*) "hasCachedInterstitial";
    func[3].functionData = NULL;
    func[3].function = &CBHasCachedInterstitial;

    *functionsToSet = func;

    AirChartboostCtx = ctx;

    NSLog(@"Exiting ContextInitializer()");
}

/* ContextFinalizer()
 * The context finalizer is called when the extension's ActionScript code
 * calls the ExtensionContext instance's dispose() method.
 * If the AIR runtime garbage collector disposes of the ExtensionContext instance, the runtime also calls ContextFinalizer().
 */
void ContextFinalizer(FREContext ctx) 
{
    NSLog(@"Entering ContextFinalizer()");

    // Nothing to clean up.
    NSLog(@"Exiting ContextFinalizer()");
    return;
}


