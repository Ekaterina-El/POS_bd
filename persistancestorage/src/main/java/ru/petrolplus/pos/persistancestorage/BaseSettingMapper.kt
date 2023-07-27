package ru.petrolplus.pos.persistancestorage

interface BaseSettingMapper <T> {
    fun toBaseSetting(setting: T): BaseSetting
    fun toT(setting: BaseSetting): T
    fun toListBaseSetting(settings: List<T>): List<BaseSetting>
}