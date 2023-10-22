package com.dmarinac.revenuekt.results

import com.revenuecat.purchases.CustomerInfo

data class PurchasesLoginResult(
    val customerInfo: CustomerInfo,
    val created: Boolean
)