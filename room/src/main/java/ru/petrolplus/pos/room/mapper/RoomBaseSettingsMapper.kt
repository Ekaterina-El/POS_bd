package ru.petrolplus.pos.room.mapper

import ru.petrolplus.pos.persistancestorage.BaseSetting
import ru.petrolplus.pos.persistancestorage.BaseSettingMapper
import ru.petrolplus.pos.room.entity.BaseSettingsEntity

class RoomBaseSettingsMapper : BaseSettingMapper<BaseSettingsEntity> {
  override fun toBaseSetting(setting: BaseSettingsEntity): BaseSetting {
    return with(setting) {
      BaseSetting(id, acquirerId, terminalId, hostIp, hostPort)
    }
  }

  override fun toT(setting: BaseSetting): BaseSettingsEntity {
    return with(setting) {
      BaseSettingsEntity(id, acquirerId, terminalId, hostIp, hostPort)
    }
  }

  override fun toListBaseSetting(settings: List<BaseSettingsEntity>) =
    settings.map { toBaseSetting(it) }

  companion object {
    private var INSTANCE: RoomBaseSettingsMapper? = null
    private val LOCK = Any()
    fun getInstance(): RoomBaseSettingsMapper {
      INSTANCE?.let { return it }

      synchronized(LOCK) {
        INSTANCE?.let { return it }

        val mapper = RoomBaseSettingsMapper()
        INSTANCE = mapper
        return mapper
      }
    }
  }
}