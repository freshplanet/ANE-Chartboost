Air Native Extension for Chartboost (iOS + Android)
======================================

This is an [Air native extension](http://www.adobe.com/devnet/air/native-extensions-for-air.html) for [Chartboost](http://chartboost.com) SDK on iOS and Android. It has been developed by [FreshPlanet](http://freshplanet.com) and is used in the game [SongPop](http://songpop.fm).


Chartboost SDK
---------

This ANE uses Chartboost SDK version 3.2.1 on iOS and 3.1.5 on Android.


Installation
---------

The ANE binary (AirChartboost.ane) is located in the *bin* folder. You should add it to your application project's Build Path and make sure to package it with your app (more information [here](http://help.adobe.com/en_US/air/build/WS597e5dadb9cc1e0253f7d2fc1311b491071-8000.html)).

On Android, you should add the following activity in your application descriptor:

    ```xml
    <activity android:name="com.chartboost.sdk.CBDialogActivity"
              android:configChanges="orientation|keyboard|keyboardHidden"
              android:windowSoftInputMode="adjustResize"
              android:theme="@android:style/Theme.Translucent"
              android:launchMode="singleTop">
    </activity>
    ```


Usage
-----

Most features from Chartboost SDK are replicated and documented in the __AirChartboost__ Actionscript class. Note that Chartboost's _More Apps_ feature is not implemented at this point.


Build script
---------

Should you need to edit the extension source code and/or recompile it, you will find an ant build script (build.xml) in the *build* folder:

    cd /path/to/the/ane/build
    mv example.build.config build.config
    #edit the build.config file to provide your machine-specific paths
    ant


Authors
------

This ANE has been written by [Alexis Taugeron](http://alexistaugeron.com). It belongs to [FreshPlanet Inc.](http://freshplanet.com) and is distributed under the [Apache Licence, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).