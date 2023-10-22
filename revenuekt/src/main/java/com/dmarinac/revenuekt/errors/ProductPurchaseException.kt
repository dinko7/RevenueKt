package com.dmarinac.revenuekt.errors

import com.revenuecat.purchases.PurchasesError

data class ProductPurchaseException(
    private val purchasesError: PurchasesError,
    val userCanceled: Boolean,
) : PurchasesException(purchasesError)