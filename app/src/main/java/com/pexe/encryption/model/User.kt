package com.pexe.encryption.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (@PrimaryKey val userName: String, val password: String)