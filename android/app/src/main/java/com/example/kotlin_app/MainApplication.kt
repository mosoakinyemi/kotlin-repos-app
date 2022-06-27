package com.example.kotlin_app

import android.app.Application
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(), ReactApplication {
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
    }
    private val reactNativeHost =
        object : ReactNativeHost(this) {
            override fun getUseDeveloperSupport() = BuildConfig.DEBUG
            override fun getPackages(): List<ReactPackage> {
                val packages = PackageList(this).getPackages()
                // Packages that cannot be autolinked yet can be added manually here
                return packages
            }
        }
    override fun getReactNativeHost(): ReactNativeHost = reactNativeHost
}
