package com.pexe.encryption.repository.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.commonsware.cwac.saferoom.SafeHelperFactory
import com.pexe.encryption.model.User


@Database(entities = [(User::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDatabase? = null
//        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//            }
//        }

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                val factory = SafeHelperFactory("123ABC".toCharArray())
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Encryption")
                    .openHelperFactory(factory)
//                        .addMigrations(MIGRATION_1_2)
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }

    abstract fun userDAO(): UserDAO
}