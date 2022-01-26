package com.peter.digicarUser.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConsultationModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val timeTxt: String,
    val txt: String,
    var isFav:Boolean
)