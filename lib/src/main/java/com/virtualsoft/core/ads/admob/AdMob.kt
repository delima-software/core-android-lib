package com.virtualsoft.core.ads.admob

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.virtualsoft.core.designpatterns.builder.IBuilder
import com.virtualsoft.core.utils.AppUtils.isDebugging

class AdMob : IAdMob {

    //DEBUG
    private var debugBannerAdUnitId: String = "ca-app-pub-3940256099942544/6300978111"
    private var debugInterstitialAdUnitId: String = "ca-app-pub-3940256099942544/1033173712"

    //RELEASE
    private var bannerAdUnitId: String = "ca-app-pub-3940256099942544/6300978111"
    private var interstitialAdUnitId: String = "ca-app-pub-3940256099942544/1033173712"

    private var bannerAd: AdView? = null
    private var interstitialAd: InterstitialAd? = null

    class Builder : IBuilder<AdMob> {

        override val building = AdMob()

        fun setBannerAdUnitId(bannerAdUnitId: String): Builder {
            building.bannerAdUnitId = bannerAdUnitId
            return this
        }

        fun setInterstitialAdUnitId(interstitialAdUnitId: String): Builder {
            building.interstitialAdUnitId = interstitialAdUnitId
            return this
        }

        fun initialize(context: Context): Builder {
            building.bannerAd = AdView(context)
            MobileAds.initialize(context) {}
            return this
        }

        override fun build(): AdMob {
            return building
        }
    }

    override fun getBannerAdView(): AdView? {
        return bannerAd
    }

    override fun loadBannerAd(context: Context, callback: ((Boolean) -> Unit)?) {
        var adUnitId = debugBannerAdUnitId
        if (!context.isDebugging())
            adUnitId = bannerAdUnitId
        bannerAd?.adUnitId = adUnitId
        bannerAd?.adSize = AdSize.BANNER
        bannerAd?.adListener = object : AdListener() {
            override fun onAdClosed() {
                bannerAd?.loadAd(AdRequest.Builder().build())
            }

            override fun onAdLoaded() {
                callback?.invoke(true)
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                callback?.invoke(false)
            }
        }
        bannerAd?.loadAd(AdRequest.Builder().build())
    }

    private fun loadInterstitialAd(context: Context, callback: ((Boolean) -> Unit)? = null) {
        var adUnitId = debugInterstitialAdUnitId
        if (!context.isDebugging())
            adUnitId = interstitialAdUnitId
        val fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                interstitialAd = null
            }
        }
        InterstitialAd.load(context,
            adUnitId,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(p0: InterstitialAd) {
                    interstitialAd = p0
                    interstitialAd?.fullScreenContentCallback = fullScreenContentCallback
                    callback?.invoke(true)
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    callback?.invoke(false)
                }
            })
    }

    override fun showInterstitialAd(context: Context, activity: Activity, callback: ((Boolean) -> Unit)?) {
        if (interstitialAd != null) {
            interstitialAd?.show(activity)
            loadInterstitialAd(context) { }
            callback?.invoke(true)
        } else {
            loadInterstitialAd(context) { hasLoaded ->
                if (hasLoaded) {
                    interstitialAd?.show(activity)
                    loadInterstitialAd(context) { }
                    callback?.invoke(true)
                }
                else {
                    callback?.invoke(false)
                }
            }
        }
    }
}