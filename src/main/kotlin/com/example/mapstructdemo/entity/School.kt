package com.example.mapstructdemo.entity

import java.util.Date

data class School (
    val id: Long,
    val schoolName: String,
    var studentsCount: Int,
    val student: Student
)