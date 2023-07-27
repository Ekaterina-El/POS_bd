package ru.petrolplus.pos

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.petrolplus.pos.persistancestorage.BaseSetting
import ru.petrolplus.pos.persistancestorage.PersistanceStorage
import ru.petrolplus.pos.room.RoomPersistenceStorage
import kotlin.random.Random
import kotlin.random.nextInt

class TestViewModel(application: Application) : AndroidViewModel(application) {
  companion object {
    const val TAG = "TEST_VIEW_MODEL"
  }

  private val persistenceStorage: PersistanceStorage =
    RoomPersistenceStorage.getInstance(application)

  fun addBaseSetting(baseSetting: BaseSetting) {
    viewModelScope.launch {
      val settingId = persistenceStorage.addBaseSettings(baseSetting)
      getById(settingId) { setting ->
        Log.d(TAG, "Added $setting")
      }
    }
  }

  fun loadAll() {
    viewModelScope.launch {
      val settings = persistenceStorage.loadAddBaseSettings()
      Log.d(TAG, "Loaded all: $settings")
    }
  }

  fun getById(id: Long, showLog: Boolean = false, onSuccess: suspend (BaseSetting) -> Unit = {}) {
    viewModelScope.launch {
      val setting = persistenceStorage.loadBaseSettingById(id)
      if (showLog) Log.d(TAG, "Taken by id: $setting")
      setting?.let { onSuccess(it) }
    }
  }

  fun testUpdate() {
    viewModelScope.launch {
      getById(1) { gettedBaseSetting ->
        val updated = gettedBaseSetting.copy(hostPort = 45633, hostIp = generateFakeIP())
        persistenceStorage.updateBaseSetting(updated)
        getById(1) { updatedBaseSetting ->
          Log.d(TAG, "Updated: $gettedBaseSetting => $updatedBaseSetting")
        }
      }
    }
  }

  private fun generateFakeIP(): String {
    val parts = mutableListOf<Int>()
    repeat(4) {
      val start = if (it == 0) 1 else 0
      parts.add(Random.nextInt(start..256))
    }
    return parts.joinToString(".")
  }

  fun addFakeSetting() {
    val faceSetting = BaseSetting(
      acquirerId = Random.nextInt(0..10000),
      terminalId = Random.nextInt(0..10000),
      hostIp = generateFakeIP(),
      hostPort = Random.nextInt(11111..100000)
    )
    addBaseSetting(baseSetting = faceSetting)
  }
}