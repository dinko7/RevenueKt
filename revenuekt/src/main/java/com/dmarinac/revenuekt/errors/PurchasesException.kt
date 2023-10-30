package com.dmarinac.revenuekt.errors

import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.PurchasesErrorCode

open class PurchasesException(
    private val purchasesError: PurchasesError
) : Exception() {
    val code: PurchasesErrorCode
        get() = purchasesError.code

    override val message: String
        get() = purchasesError.message

    val underlyingErrorMessage: String?
        get() = purchasesError.underlyingErrorMessage

    override fun toString() = purchasesError.toString()
}