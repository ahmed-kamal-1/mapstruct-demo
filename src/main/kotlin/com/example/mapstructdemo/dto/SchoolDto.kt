package com.example.mapstructdemo.dto

data class SchoolDto (
    val id: Long,
    var name: String,
    var status: String?,
    val student: StudentDto
)