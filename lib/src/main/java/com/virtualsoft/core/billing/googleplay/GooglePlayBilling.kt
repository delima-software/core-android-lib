package com.virtualsoft.core.billing.googleplay

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.*
import com.virtualsoft.core.designpatterns.builder.IBuilder
import kotlinx.coroutines.*

class GooglePlayBilling(val context: Context) : IGooglePlayBilling, PurchasesUpdatedListener {

    override val billingClient = BillingClient.newBuilder(context)
        .setListener(this)
        .enablePendingPurchases()
        .build()

    private var billingServiceDisconnectedListener: (() -> Unit)? = null
    private var userCancelledPurchaseListener: (() -> Unit)? = null
    private var billingErrorListener: (() -> Unit)? = null
    private var acknowledgePurchaseListener: ((Purchase) -> Unit)? = null

    class Builder(val context: Context) : IBuilder<GooglePlayBilling> {

        override val building = GooglePlayBilling(context)

        fun setBillingServiceDisconnectedListener(billingServiceDisconnectedListener: () -> Unit): Builder {
            building.billingServiceDisconnectedListener = billingServiceDisconnectedListener
            return this
        }

        fun setUserCancelledPurchaseListener(userCancelledPurchaseListener: () -> Unit): Builder {
            building.userCancelledPurchaseListener = userCancelledPurchaseListener
            return this
        }

        fun setBillingErrorListener(billingErrorListener: () -> Unit): Builder {
            building.billingErrorListener = billingErrorListener
            return this
        }

        fun setAcknowledgePurchaseListener(acknowledgePurchaseListener: (Purchase) -> Unit): Builder {
            building.acknowledgePurchaseListener = acknowledgePurchaseListener
            return this
        }
    }

    override fun isReady(): Boolean {
        return billingClient.isReady
    }

    override fun startConnection(callback: (Boolean) -> Unit) {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(p0: BillingResult?) {
                if (p0?.responseCode == BillingClient.BillingResponseCode.OK)
                    callback(true)
                else
                    callback(false)
            }

            override fun onBillingServiceDisconnected() {
                billingServiceDisconnectedListener?.invoke()
            }
        })
    }

    override fun endConnection() {
        billingClient.endConnection()
    }

    override fun queryPurchases(skuType: String): Purchase.PurchasesResult {
        return billingClient.queryPurchases(skuType)
    }

    override fun queryPurchaseHistoryAsync(
        skuType: String,
        listener: PurchaseHistoryResponseListener
    ) {
        billingClient.queryPurchaseHistoryAsync(skuType, listener)
    }

    override suspend fun queryInAppSkuDetails(skuList: List<String>): SkuDetailsResult? {
        if (billingClient.isReady) {
            val params = SkuDetailsParams.newBuilder()
            params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)
            return withContext(Dispatchers.IO) {
                billingClient.querySkuDetails(params.build())
            }
        }
        return null
    }

    override suspend fun querySubsSkuDetails(skuList: List<String>): SkuDetailsResult? {
        if (billingClient.isReady) {
            val params = SkuDetailsParams.newBuilder()
            params.setSkusList(skuList).setType(BillingClient.SkuType.SUBS)
            return withContext(Dispatchers.IO) {
                billingClient.querySkuDetails(params.build())
            }
        }
        return null
    }

    override fun launchBillingFlow(activity: Activity, skuDetails: SkuDetails) {
        if (billingClient.isReady) {
            val flowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetails)
                .build()
            billingClient.launchBillingFlow(activity, flowParams)
        }
    }

    override fun onPurchasesUpdated(p0: BillingResult?, p1: MutableList<Purchase>?) {
        if (p0?.responseCode == BillingClient.BillingResponseCode.OK && p1 != null) {
            GlobalScope.launch {
                for (purchase in p1)
                    handlePurchase(purchase)
            }
        }
        else if (p0?.responseCode == BillingClient.BillingResponseCode.USER_CANCELED)
            userCancelledPurchaseListener?.invoke()
        else
            billingErrorListener?.invoke()
    }

    private suspend fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged) {
                acknowledgePurchaseListener?.invoke(purchase)
                val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()
                withContext(Dispatchers.IO) {
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams)
                }
            }
        }
    }
}