package ru.petrolplus.pos.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.petrolplus.pos.persistancestorage.PersistanceStorage
import ru.petrolplus.pos.room.entity.BaseSettingsEntity

/**
 * DAO для работы с таблицей "BASE_SETTINGS".
 */
@Dao
interface BaseSettingsDao: PersistanceStorage <BaseSettingsEntity> {
    /**
     * Вставляет настройки в таблицу "BASE_SETTINGS".
     *
     * @param settings Данные для вставки.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override fun addBaseSettings(settings: BaseSettingsEntity)

    /**
     * Запрашивает настройки из таблицы "BASE_SETTINGS" по заданному ID.
     *
     * @param id ID для запроса.
     * @return [BaseSettingsEntity] с данными для заданного ID или null
     */
    @Query("SELECT * FROM BASE_SETTINGS WHERE ID = :id")
    override fun loadBaseSettingById(id: Long): BaseSettingsEntity?

    /**
     * Запрашивает все настройки из таблицы "BASE_SETTINGS".
     *
     * @return Коллекция [BaseSettingsEntity] с настройками.
     */
    @Query("SELECT * FROM BASE_SETTINGS")
    override fun loadAddBaseSettings(): List<BaseSettingsEntity>

    /**
     * Обновляет настройки в таблице "BASE_SETTINGS".
     *
     * @param settings Обновленные настройки.
     */
    @Update
    override fun updateBaseSetting(settings: BaseSettingsEntity)
}
