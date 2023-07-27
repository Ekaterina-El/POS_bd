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
  suspend fun <T: BaseSetting> addBaseSettings(data: T)

  /**
   * Загружает все настройки из базы данных.
   *
   * @return Загруженные данные
   */
  suspend fun <T: BaseSetting> loadAddBaseSettings(): List<T>

  /**
   * Загружает настройки по id из базы данных.
   *
   * @param id Идентификатор настроек
   * @return Загруженные найстройки или null
   */
  suspend fun <T: BaseSetting> loadBaseSettingById(id: Long): T?

  /**
   * Обновляет данные в базе данных.
   *
   * @param data Новые данные для обновления.
   */
  suspend fun <T: BaseSetting> updateBaseSetting(data: T)
}