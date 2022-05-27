package com.suki.thewheel.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WheelEntry(
    val name: String,
    @PrimaryKey val id: Int? = null
)
