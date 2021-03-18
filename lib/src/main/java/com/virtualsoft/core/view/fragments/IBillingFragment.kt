package com.virtualsoft.core.view.fragments

interface IBillingFragment {

    suspend fun checkPremium(): Boolean
}