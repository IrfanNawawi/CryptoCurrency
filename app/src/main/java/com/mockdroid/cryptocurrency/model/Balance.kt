package com.mockdroid.cryptocurrency.model

data class Balance(
    val status: String,
    val data: Data
)

data class Data(
    val error_message: String?,
    val network: String?,
    val available_balance: String?,
    val pending_received_balance: String?,
    val txs: Array<String>?
)