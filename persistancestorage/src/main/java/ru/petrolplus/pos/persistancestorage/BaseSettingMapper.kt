package ru.petrolplus.pos.persistancestorage

interface BaseSettingMapper <T> {

    /**
     * Преобразует в [BaseSetting] сущность [T]
     *
     * @param setting экземпляр класса [T] на основе которого будет создан экземпляр [BaseSetting]
     * @return Экземпляр [BaseSetting] на основе [T]
     */
    fun toBaseSetting(setting: T): BaseSetting

    /**
     * Преобразует в [T] сущность [BaseSetting]
     *
     * @param setting экземпляр класса [BaseSetting] на основе которого будет создан экземпляр [T]
     * @return Экземпляр [T] на основе [BaseSetting]
     */
    fun toT(setting: BaseSetting): T

    /**
     * Преобразует в коллекцию [BaseSetting] коллекцию [T]
     *
     * @param settings коллекция [T] на основе которой будет создана коллекция [BaseSetting]
     * @return Коллекция [BaseSetting] на основе коллекции [T]
     */
    fun toListBaseSetting(settings: List<T>): List<BaseSetting>
}