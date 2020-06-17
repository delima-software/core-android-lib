package com.virtualsoft.core.ads.admob

import android.content.Context
import com.google.android.gms.ads.*
import com.virtualsoft.core.designpatterns.builder.IBuilder
import com.virtualsoft.core.utils.AppUtils.isDebugging

class AdMob(val context: Context) : IAdMob {

    //DEBUG
    var debugBannerAdUnitId: String = "ca-app-pub-3940256099942544/6300978111"
        private set
    var debugInterstitialAdUnitId: String = "ca-app-pub-3940256099942544/1033173712"
        private set

    //RELEASE
    var bannerAdUnitId: String = "ca-app-pub-3940256099942544/6300978111"
        private set
    var interstitialAdUnitId: String = "ca-app-pub-3940256099942544/1033173712"
        private set

    override val bannerAd: AdView = AdView(context)
    override val interstitialAd: InterstitialAd = InterstitialAd(context)

    class Builder(val context: Context) : IBuilder<AdMob> {

        override val building = AdMob(context)

        fun setBannerAdUnitId(bannerAdUnitId: String): Builder {
            building.bannerAdUnitId = bannerAdUnitId
            return this
        }

        fun setInterstitialAdUnitId(interstitialAdUnitId: String): Builder {
            building.interstitialAdUnitId = interstitialAdUnitId
            return this
        }

        override fun build(): AdMob {
            MobileAds.initialize(context) {}
            initBanner()
            initInterstitial()
            return building
        }

        private fun initBanner() {
            var adUnitId = building.debugBannerAdUnitId
            if (!context.isDebugging())
                adUnitId = building.bannerAdUnitId
            building.bannerAd.adUnitId = adUnitId
            building.bannerAd.adSize = AdSize.BANNER
            building.bannerAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    building.bannerAd.loadAd(AdRequest.Builder().build())
                }
            }
            building.bannerAd.loadAd(AdRequest.Builder().build())
        }

        private fun initInterstitial() {
            var adUnitId = building.debugInterstitialAdUnitId
            if (!context.isDebugging())
                adUnitId = building.interstitialAdUnitId
            building.interstitialAd.adUnitId = adUnitId
            building.interstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    building.interstitialAd.loadAd(AdRequest.Builder().build())
                }
            }
            building.interstitialAd.loadAd(AdRequest.Builder().build())
        }
    }

    override fun setBannerAdListener(adListener: AdListener) {
        bannerAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                bannerAd.loadAd(AdRequest.Builder().build())
                adListener.onAdClosed()
            }

            override fun onAdFailedToLoad(var1: Int) {
                adListener.onAdFailedToLoad(var1)
            }

            override fun onAdLeftApplication() {
                adListener.onAdLeftApplication()
            }

            override fun onAdOpened() {
                adListener.onAdOpened()
            }

            override fun onAdLoaded() {
                adListener.onAdLoaded()
            }

            override fun onAdClicked() {
                adListener.onAdClicked()
            }

            override fun onAdImpression() {
                adListener.onAdImpression()
            }
        }
    }

    override fun setInterstitialAdListener(adListener: AdListener) {
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                interstitialAd.loadAd(AdRequest.Builder().build())
                adListener.onAdClosed()
            }

            override fun onAdFailedToLoad(var1: Int) {
                adListener.onAdFailedToLoad(var1)
            }

            override fun onAdLeftApplication() {
                adListener.onAdLeftApplication()
            }

            override fun onAdOpened() {
                adListener.onAdOpened()
            }

            override fun onAdLoaded() {
                adListener.onAdLoaded()
            }

            override fun onAdClicked() {
                adListener.onAdClicked()
            }

            override fun onAdImpression() {
                adListener.onAdImpression()
            }
        }
    }
}