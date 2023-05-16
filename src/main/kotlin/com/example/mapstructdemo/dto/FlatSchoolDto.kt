package com.example.mapstructdemo.dto

data class FlatSchoolDto (
    val id: Long,
    val schoolName: String,
    val studentName: String,
    val studentAge: Int,
    val studentAddressArea: String,
    val studentAddressStreet: String?
)