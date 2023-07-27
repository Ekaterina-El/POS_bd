package ru.petrolplus.pos.persistancestorage

/**
 * Обобщающий интерфейс для работы с базой данных.
 * Этот интерфейс предоставляет абстракцию для записи, чтения и обновления данных.
 * Он позволяет использовать различные реализации базы данных (например, Room, Realm, SQLite и т.д.)
 * без привязки к конкретной библиотеке.
 *
 * Для работы с базой данных, создайте класс реализующий этот интерфейс.
 *
 * @see RoomPersistenceStorage
 */
interface PersistanceStorage <T> {
    /**
     * Сохраняет данные в базе данных.
     *
     * @param data Данные для сохранения.
     */
    fun saveData(data: T)

    /**
     * Загружает данные из базы данных.
     *
     * @return Загруженные данные или пустая строка, если данных нет.
     */
    fun loadData(): List<T>

    /**
     * Обновляет данные в базе данных.
     *
     * @param data Новые данные для обновления.
     */
    fun updateData(data: T)
}