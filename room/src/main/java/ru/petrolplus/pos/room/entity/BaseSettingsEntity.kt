package ru.petrolplus.pos.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения данных в таблице "BASE_SETTINGS".
 *
 * @param acquirerId Номер эквайера (эмитент терминала).
 * @param terminalId Номер терминала.
 * @param hostIp IP-адрес для подключения.
 * @param hostPort Порт для подключения.
 */
@Entity(tableName = "BASE_SETTINGS")
data class BaseSettingsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Long = 0,

    @ColumnInfo(name = "ACQUIRER_ID")
    val acquirerId: Int,

    @ColumnInfo(name = "TERMINAL_ID")
    val terminalId: Int,

    @ColumnInfo(name = "HOST_IP")
    val hostIp: String,

    @ColumnInfo(name = "HOST_PORT")
    val hostPort: Int
)
