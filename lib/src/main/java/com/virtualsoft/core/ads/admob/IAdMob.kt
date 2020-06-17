package com.virtualsoft.core.ads.admob

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.virtualsoft.core.ads.IAdProvider

interface IAdMob : IAdProvider {

    val bannerAd: AdView
    val interstitialAd: InterstitialAd

    fun setBannerAdListener(adListener: AdListener)

    fun setInterstitialAdListener(adListener: AdListener)
}