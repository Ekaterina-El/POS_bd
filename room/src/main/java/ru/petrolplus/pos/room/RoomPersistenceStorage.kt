package ru.petrolplus.pos.room

import android.app.Application
import ru.petrolplus.pos.persistancestorage.BaseSetting
import ru.petrolplus.pos.persistancestorage.PersistanceStorage
import ru.petrolplus.pos.room.dao.BaseSettingsDao
import ru.petrolplus.pos.room.mapper.RoomBaseSettingsMapper

/**
 * Реализация базы данных с использованием Room.
 */
class RoomPersistenceStorage private constructor(
  private val dao: BaseSettingsDao, private val mapper: RoomBaseSettingsMapper
) : PersistanceStorage<BaseSetting> {
  override fun addBaseSettings(setting: BaseSetting) {
    dao.addBaseSettings(mapper.toT(setting))
  }

  override fun loadAddBaseSettings() = mapper.toListBaseSetting(dao.loadAddBaseSettings())


  override fun loadBaseSettingById(id: Long): BaseSetting? =
    dao.loadBaseSettingById(id)?.let { mapper.toBaseSetting(it) }

  override fun updateBaseSetting(setting: BaseSetting) {
    dao.updateBaseSetting(mapper.toT(setting))
  }

  companion object {
    private var INSTANCE: RoomPersistenceStorage? = null
    private val LOCK = Any()
    fun getInstance(application: Application): RoomPersistenceStorage {
      INSTANCE?.let { return it }

      synchronized(LOCK) {
        INSTANCE?.let { return it }

        val dao = SettingsDatabase.getInstance(application).baseSettingsDao()
        val mapper = RoomBaseSettingsMapper.getInstance()
        val storage = RoomPersistenceStorage(dao, mapper)

        INSTANCE = storage
        return storage
      }
    }
  }
}