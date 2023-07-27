package ru.petrolplus.pos.persistancestorage

data class BaseSetting(
    var id: Long = 0,
    val acquirerId: Int,
    val terminalId: Int,
    val hostIp: String,
    val hostPort: Int
)