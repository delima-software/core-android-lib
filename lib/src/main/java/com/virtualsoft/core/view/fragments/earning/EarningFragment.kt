package com.virtualsoft.core.view.fragments.earning

import android.app.Activity
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.android.gms.ads.AdView
import com.virtualsoft.core.ads.AdProviders
import com.virtualsoft.core.view.fragments.BaseFragment
import kotlin.random.Random

abstract class EarningFragment : BaseFragment(), IAdFragment, IBillingFragment {

    override var chanceShowBanner: Int = 100
    override var chanceShowInterstitial: Int = 10

    override fun setupAds() { }

    override fun showAds(callback: () -> Unit) { }

    override fun getBannerAdView(): AdView? {
        return AdProviders.adMob?.getBannerAdView()
    }

    override fun setupBanner(relativeLayout: RelativeLayout?) {
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

    override fun tryLoadBanner(callback: ((Boolean) -> Unit)?) {
        val chance = Random.nextInt(0, 100)
        if (chance <= chanceShowBanner) {
            AdProviders.adMob?.loadBannerAd(requireContext(), callback)
        }
        callback?.invoke(false)
    }

    override fun tryShowInterstitial(activity: Activity, callback: ((Boolean) -> Unit)?) {
        val chance = Random.nextInt(0, 100)
        if (chance <= chanceShowInterstitial) {
            AdProviders.adMob?.showInterstitialAd(requireContext(), activity, callback)
        }
        callback?.invoke(false)
    }

    override suspend fun checkPremium(): Boolean {
        return false
    }

    override suspend fun changePremium(isPremium: Boolean): Boolean {
        return false
    }
}