package com.example.mapstructdemo.dto

data class StudentDto (
    val id: Long,
    val name: String,
    val age: Int,
    val address: StudentAddressDto
)