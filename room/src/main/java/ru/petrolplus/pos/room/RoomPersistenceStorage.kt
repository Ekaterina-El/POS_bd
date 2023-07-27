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
  override suspend fun addBaseSettings(setting: BaseSetting) {
    dao.addBaseSettings(mapper.toT(setting))
  }

  override suspend fun loadAddBaseSettings() = mapper.toListBaseSetting(dao.loadAddBaseSettings())


  override suspend fun loadBaseSettingById(id: Long): BaseSetting? =
    dao.loadBaseSettingById(id)?.let { mapper.toBaseSetting(it) }

  override suspend fun updateBaseSetting(setting: BaseSetting) {
    dao.updateBaseSetting(mapper.toT(setting))
  }

  companion object {
    private var INSTANCE: RoomPersistenceStorage? = null
    private val LOCK = Any()

    /**
     * Предоставляет Singleton экземпляр [RoomPersistenceStorage]
     *
     * @param application контекст приложения.
     * @return Singleton экземпляр [RoomPersistenceStorage]
     */
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