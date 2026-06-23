package com.nauli.anmp_uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nauli.anmp_uts.model.dao.HabitDao

@Database(
    entities = [Habit::class],
    version = 1,
    exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: HabitDatabase? = null

        fun getInstance(context: Context): HabitDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}