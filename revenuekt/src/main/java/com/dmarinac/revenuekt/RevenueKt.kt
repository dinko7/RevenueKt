package com.dmarinac.revenuekt

import com.dmarinac.revenuekt.errors.ProductPurchaseException
import com.dmarinac.revenuekt.errors.PurchasesException
import com.dmarinac.revenuekt.helpers.SuspendableReceiveCustomerInfo
import com.dmarinac.revenuekt.results.PurchaseResult
import com.dmarinac.revenuekt.results.PurchasesLoginResult
import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.Offerings
import com.revenuecat.purchases.ProductType
import com.revenuecat.purchases.PurchaseParams
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.interfaces.GetStoreProductsCallback
import com.revenuecat.purchases.interfaces.LogInCallback
import com.revenuecat.purchases.interfaces.PurchaseCallback
import com.revenuecat.purchases.interfaces.ReceiveOfferingsCallback
import com.revenuecat.purchases.models.StoreProduct
import com.revenuecat.purchases.models.StoreTransaction
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Throws(PurchasesException::class)
suspend fun Purchases.getOfferings(): Offerings {
    return suspendCoroutine { continuation ->
        getOfferings(object : ReceiveOfferingsCallback {
            override fun onError(error: PurchasesError) {
                continuation.resumeWithException(PurchasesException(error))
            }

            override fun onReceived(offerings: Offerings) {
                continuation.resume(offerings)
            }
        })
    }
}

@Throws(PurchasesException::class)
suspend fun Purchases.getCustomerInfo(): CustomerInfo {
    return suspendCoroutine { continuation ->
        getCustomerInfo(SuspendableReceiveCustomerInfo(continuation))
    }
}

@Throws(ProductPurchaseException::class)
suspend fun Purchases.purchase(params: PurchaseParams): PurchaseResult {
    return suspendCoroutine { continuation ->
        purchase(params, object : PurchaseCallback {
            override fun onCompleted(
                storeTransaction: StoreTransaction,
                customerInfo: CustomerInfo
            ) {
                continuation.resume(PurchaseResult(storeTransaction, customerInfo))
            }

            override fun onError(error: PurchasesError, userCancelled: Boolean) {
                continuation.resumeWithException(ProductPurchaseException(error, userCancelled))
            }
        })
    }
}

@Throws(PurchasesException::class)
suspend fun Purchases.logIn(newAppUserID: String): PurchasesLoginResult {
    return suspendCoroutine { continuation ->
        logIn(newAppUserID, object : LogInCallback {
            override fun onError(error: PurchasesError) {
                continuation.resumeWithException(PurchasesException(error))
            }

            override fun onReceived(customerInfo: CustomerInfo, created: Boolean) {
                continuation.resume(PurchasesLoginResult(customerInfo, created))
            }
        })
    }
}

@Throws(PurchasesException::class)
suspend fun Purchases.logOut(): CustomerInfo {
    return suspendCoroutine { continuation ->
        logOut(SuspendableReceiveCustomerInfo(continuation))
    }
}

@Throws(PurchasesException::class)
suspend fun Purchases.getProducts(
    productIds: List<String>,
    type: ProductType? = null
): List<StoreProduct> {
    return suspendCoroutine { continuation ->
        getProducts(productIds, type, object : GetStoreProductsCallback {
            override fun onError(error: PurchasesError) {
                continuation.resumeWithException(PurchasesException(error))
            }

            override fun onReceived(storeProducts: List<StoreProduct>) {
                continuation.resume(storeProducts)
            }
        })
    }
}

@Throws(PurchasesException::class)
suspend fun Purchases.restorePurchases(): CustomerInfo {
    return suspendCoroutine { continuation ->
        restorePurchases(SuspendableReceiveCustomerInfo(continuation))
    }
}