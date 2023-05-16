package com.example.mapstructdemo.mapper

import com.example.mapstructdemo.dto.StudentDto
import com.example.mapstructdemo.entity.Student
import org.mapstruct.Mapper

@Mapper
interface StudentMapper {

    fun toDto(student: Student):StudentDto

}