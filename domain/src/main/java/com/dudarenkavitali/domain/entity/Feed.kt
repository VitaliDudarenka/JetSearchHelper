package com.dudarenkavitali.domain.entity

data class Feed(
    val longitude: Double,
    val latitude: Double,
    val registration: String,
    val airCraft: String,
    val airCompany: String,
    val to: String,
    val from: String
) {
}