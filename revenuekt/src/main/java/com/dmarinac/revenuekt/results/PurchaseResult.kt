package com.dmarinac.revenuekt.results

import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.models.StoreTransaction

data class PurchaseResult(
    val storeTransaction: StoreTransaction,
    val customerInfo: CustomerInfo
)
