package com.virtualsoft.core.view.fragments

import android.app.Activity
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
        return AdProviders.adMob?.getBannerAdView()
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

    fun tryLoadBanner(callback: ((Boolean) -> Unit)? = null) {
        val chance = Random.nextInt(0, 100)
        if (chance <= chanceShowBanner) {
            AdProviders.adMob?.loadBannerAd(callback)
        }
        callback?.invoke(false)
    }

    fun tryShowInterstitial(activity: Activity, callback: ((Boolean) -> Unit)? = null) {
        val chance = Random.nextInt(0, 100)
        if (chance <= chanceShowInterstitial) {
            AdProviders.adMob?.showInterstitialAd(activity, callback)
        }
        callback?.invoke(false)
    }
}