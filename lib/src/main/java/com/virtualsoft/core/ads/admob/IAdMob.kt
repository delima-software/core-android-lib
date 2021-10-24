package com.virtualsoft.core.ads.admob

import android.app.Activity
import com.google.android.gms.ads.AdView
import com.virtualsoft.core.ads.IAdProvider

interface IAdMob : IAdProvider {

    fun getBannerAdView(): AdView

    fun loadBannerAd(callback: ((Boolean) -> Unit)? = null)

    fun showInterstitialAd(activity: Activity, callback: ((Boolean) -> Unit)? = null)
}