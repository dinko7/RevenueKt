# RevenueKt
Kotlin coroutines wrapper for RevenueCat Android SDK.

## Methods Covered

| RevenueCat Method | RevenueKt Coroutine Method                                                |
|-------------------|---------------------------------------------------------------------------|
| `getOfferings(ReceiveOfferingsCallback)` | `suspend fun Purchases.getOfferings(): Offerings`                         |
| `getCustomerInfo(ReceiveCustomerInfoCallback)` | `suspend fun Purchases.getCustomerInfo(): CustomerInfo`                   |
| `purchase(PurchaseParams, PurchaseCallback)` | `suspend fun Purchases.purchase(params: PurchaseParams): PurchaseResult`  |
| `logIn(String, LogInCallback)` | `suspend fun Purchases.logIn(newAppUserID: String): PurchasesLoginResult` |
| `logOut(ReceiveCustomerInfoCallback)` | `suspend fun Purchases.logOut(): CustomerInfo`                            |
| `restorePurchases(ReceiveCustomerInfoCallback)` | `suspend fun Purchases.restorePurchases(): CustomerInfo`                  |
| `getProducts(List<String>, ProductType?, GetStoreProductsCallback)` | `suspend fun Purchases.getProducts(productIds: List<String>, type: ProductType? = null): List<StoreProduct>` |

## Installation (coming soon)

For now feel free to copy `revenuekt` to your project.

<!-- TODO: enable later when Maven Central Repo is up
### For Groovy
```gradle
dependencies {
    implementation 'com.yourusername.revenuekt:revenuekt:1.0.0'
}
```

### For Kotlin DSL (kts)
```kotlin
dependencies {
implementation("com.yourusername.revenuekt:revenuekt:1.0.0")
}
```
 -->


## Example Usage

```kotlin
try {
    val offerings = Purchases.getOfferings()
    // Process offerings
} catch (e: PurchasesException) {
    // Handle error
}

try {
    val customerInfo = Purchases.getCustomerInfo()
    // Process customer info
} catch (e: PurchasesException) {
    // Handle error
}

// ... and similarly for other methods
```

## Future Plans
- [ ] Unit Tests


## License

This project is licensed under the MIT License - a permissive license that allows for free use, modification, and distribution, provided that the license and copyright notice are included in all copies or substantial portions of the software.


