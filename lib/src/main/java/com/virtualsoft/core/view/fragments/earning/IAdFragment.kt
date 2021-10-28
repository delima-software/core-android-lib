package com.virtualsoft.core.view.fragments.earning

import android.app.Activity
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.android.gms.ads.AdView
import com.virtualsoft.core.ads.AdProviders
import com.virtualsoft.core.view.fragments.IFragment
import kotlin.random.Random

interface IAdFragment : IFragment {

    var chanceShowBanner: Int
    var chanceShowInterstitial: Int

    fun setupAds()

    fun showAds(callback: () -> Unit)

    fun getBannerAdView(): AdView?

    fun setupBanner(relativeLayout: RelativeLayout?)

    fun tryLoadBanner(callback: ((Boolean) -> Unit)? = null)

    fun tryShowInterstitial(activity: Activity, callback: ((Boolean) -> Unit)? = null)
}