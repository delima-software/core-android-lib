package com.virtualsoft.core.billing.googleplay

import android.app.Activity
import com.android.billingclient.api.*
import com.virtualsoft.core.billing.IBillingProvider

interface IGooglePlayBilling : IBillingProvider {

    var billingClient: BillingClient?

    fun isReady(): Boolean

    fun startConnection(callback: (Boolean) -> Unit)

    fun endConnection()

    fun queryPurchasesAsync(skuType: String, listener: PurchasesResponseListener)

    fun queryPurchaseHistoryAsync(skuType: String, listener: PurchaseHistoryResponseListener)

    suspend fun queryInAppSkuDetails(skuList: List<String>): SkuDetailsResult?

    suspend fun querySubsSkuDetails(skuList: List<String>): SkuDetailsResult?

    fun launchBillingFlow(activity: Activity, skuDetails: SkuDetails)
}