package com.virtualsoft.core.ads.admob

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdView
import com.virtualsoft.core.ads.IAdProvider

interface IAdMob : IAdProvider {

    fun getBannerAdView(): AdView?

    fun loadBannerAd(context: Context, callback: ((Boolean) -> Unit)? = null)

    fun showInterstitialAd(context: Context, activity: Activity, callback: ((Boolean) -> Unit)? = null)
}