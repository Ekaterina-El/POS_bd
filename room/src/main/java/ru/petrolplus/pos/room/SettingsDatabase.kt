package ru.petrolplus.pos.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.petrolplus.pos.room.dao.BaseSettingsDao
import ru.petrolplus.pos.room.entity.BaseSettingsEntity

@Database(entities = [BaseSettingsEntity::class], version = 1, exportSchema = false)
internal abstract class SettingsDatabase: RoomDatabase() {
    abstract fun baseSettingsDao(): BaseSettingsDao

    companion object {
        private var INSTANCE: SettingsDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "settings_database"

        internal fun getInstance(application: Application): SettingsDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val db = Room.databaseBuilder(application, SettingsDatabase::class.java, DB_NAME).build()
                INSTANCE = db
                return db
            }
        }
    }
}