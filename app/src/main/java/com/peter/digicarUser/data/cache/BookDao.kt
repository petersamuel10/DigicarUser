package com.peter.digicarUser.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peter.digicarUser.data.model.ConsultationModel
import com.peter.digicarUser.data.model.User


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsultation(consultation: ConsultationModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): User

    @Query("SELECT * FROM consultationmodel")
    suspend fun getAllConsultation(): List<ConsultationModel>

    @Query("UPDATE consultationmodel SET isFav = :isFav WHERE id=:id")
    suspend fun setFav(isFav: Boolean, id: Int)

    @Query("SELECT * FROM consultationmodel WHERE isFav =:isFav")
    suspend fun getFav(isFav: Boolean): List<ConsultationModel>

}