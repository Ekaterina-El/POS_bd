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
) : PersistanceStorage {
  override suspend fun <T: BaseSetting> addBaseSettings(data: T) {
    dao.addBaseSettings(mapper.toT(data))
  }

  @Suppress("UNCHECKED_CAST")
  override suspend fun <T: BaseSetting> loadAddBaseSettings(): List<T> =
    mapper.toListBaseSetting(dao.loadAddBaseSettings()) as List<T>

  @Suppress("UNCHECKED_CAST")
  override suspend fun <T: BaseSetting> loadBaseSettingById(id: Long): T? =
    dao.loadBaseSettingById(id)?.let { mapper.toBaseSetting(it) }?.let { it as T }

  override suspend fun <T: BaseSetting> updateBaseSetting(data: T) {
    dao.updateBaseSetting(mapper.toT(data))
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