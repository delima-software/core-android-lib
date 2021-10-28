package com.virtualsoft.core.view.fragments.earning

import com.virtualsoft.core.view.fragments.IFragment

interface IBillingFragment : IFragment {

    suspend fun checkPremium(): Boolean

    suspend fun changePremium(isPremium: Boolean): Boolean
}