package com.virtualsoft.core.view.fragments

import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.virtualsoft.core.ads.AdProviders
import com.virtualsoft.core.view.activities.BaseActivity
import kotlin.random.Random

interface IAdFragment {

    var chanceShowBanner: Int
    var chanceShowInterstitial: Int

    fun setupAds() { }

    fun showAds(callback: () -> Unit) { }

    fun getBannerAdView(): AdView? {
        return AdProviders.adMob?.bannerAd
    }

    fun setupBanner(relativeLayout: RelativeLayout?) {
        getBannerAdView()?.let { adView ->
            if (adView.parent != null)
                (adView.parent as? ViewGroup)?.removeView(adView)
            val layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1)
            relativeLayout?.addView(adView, layoutParams)
        }
    }

    fun showBanner(callback: (() -> Unit)? = null): Boolean {
        val chance = Random.nextInt(0, 100)
        if (chance <= chanceShowBanner) {
            AdProviders.adMob?.let { adMob ->
                adMob.setBannerAdListener(object: AdListener() {
                    override fun onAdClosed() {
                        callback?.invoke()
                    }
                })
                if (!adMob.bannerAd.isLoading) {
                    adMob.bannerAd.loadAd(AdRequest.Builder().build())
                    return true
                }
            }
        }
        return false
    }

    fun showInterstitial(callback: (() -> Unit)? = null): Boolean {
        val chance = Random.nextInt(0, 100)
        if (chance <= chanceShowInterstitial) {
            AdProviders.adMob?.let { adMob ->
                adMob.setInterstitialAdListener(object : AdListener() {
                    override fun onAdClosed() {
                        callback?.invoke()
                    }
                })
                if(adMob.interstitialAd.isLoaded) {
                    adMob.interstitialAd.show()
                    return true
                }
            }
        }
        return false
    }
}