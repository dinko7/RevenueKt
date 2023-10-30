package com.dmarinac.revenuekt.helpers

import com.dmarinac.revenuekt.errors.PurchasesException
import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.interfaces.ReceiveCustomerInfoCallback
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class SuspendableReceiveCustomerInfo(private val continuation: Continuation<CustomerInfo>) :
    ReceiveCustomerInfoCallback {
    override fun onError(error: PurchasesError) {
        continuation.resumeWithException(PurchasesException(error))
    }

    override fun onReceived(customerInfo: CustomerInfo) {
        continuation.resume(customerInfo)
    }
}