package com.example.myapplication.models

import com.google.gson.annotations.SerializedName


data class UserModel (
    @SerializedName("id") val id: Int?,
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("contactPhone") val contactPhone: String?
)