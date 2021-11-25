package com.dudarenkavitali.data.entity

import com.google.gson.annotations.SerializedName

data class ErrorResponse (@SerializedName("message") val message: String = "",
                     @SerializedName("errorCode") val errorCode: Int = 0) : DataEntity {

}