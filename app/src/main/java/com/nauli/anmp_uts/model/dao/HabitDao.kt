package com.nauli.anmp_uts.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nauli.anmp_uts.model.Habit

@Dao
interface HabitDao {

    @Query("SELECT * FROM habit")
    fun getAllHabit(): LiveData<List<Habit>>

    @Query("SELECT * FROM habit WHERE id = :id")
    fun getHabit(id: Int): Habit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)
}