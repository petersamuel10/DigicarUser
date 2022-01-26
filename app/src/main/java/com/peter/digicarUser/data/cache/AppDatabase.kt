package com.peter.digicarUser.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peter.digicarUser.data.model.ConsultationModel
import com.peter.digicarUser.data.model.User

@Database(
    entities = [User::class, ConsultationModel::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        val DATABASE_NAME: String = "digi_car_db"
    }
}