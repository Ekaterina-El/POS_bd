package ru.petrolplus.pos.persistancestorage

/**
 * Обобщающий интерфейс для работы с базой данных.
 * Этот интерфейс предоставляет абстракцию для записи, чтения и обновления данных.
 * Он позволяет использовать различные реализации базы данных (например, Room, Realm, SQLite и т.д.)
 * без привязки к конкретной библиотеке.
 *
 * Для работы с базой данных, создайте класс реализующий этот интерфейс, mapper и сущность в которой будете хранить данные.
 */
interface PersistanceStorage {
  /**
   * Добавляет данные о настройке в базу данных.
   *
   * @param data Данные для сохранения.
   */
  suspend fun addBaseSettings(data: BaseSetting)

  /**
   * Загружает все настройки из базы данных.
   *
   * @return Загруженные данные
   */
  suspend fun loadAddBaseSettings(): List<BaseSetting>

  /**
   * Загружает настройки по id из базы данных.
   *
   * @param id Идентификатор настроек
   * @return Загруженные найстройки или null
   */
  suspend fun loadBaseSettingById(id: Long): BaseSetting?


  /**
   * Обновляет данные в базе данных.
   *
   * @param data Новые данные для обновления.
   */
  suspend fun updateBaseSetting(data: BaseSetting)
}