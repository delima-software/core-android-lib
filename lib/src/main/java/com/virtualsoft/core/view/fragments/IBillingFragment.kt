package com.virtualsoft.core.view.fragments

interface IBillingFragment {

    fun checkPremium(callback: (Boolean) -> Unit) { }
}