package com.example.mapstructdemo.mapper

import com.example.mapstructdemo.dto.FlatSchoolDto
import com.example.mapstructdemo.dto.SchoolDto
import com.example.mapstructdemo.entity.School
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface SchoolMapperInterface {

    @Mapping(target="name", source = "schoolName")
    @Mapping(target = "status", ignore = true)
    fun toDto(school: School):SchoolDto

    @Mapping(target="schoolName", source = "name")
    fun toEntity(schoolDto: SchoolDto):School

    @Mapping(target="studentName", source = "student.name")
    @Mapping(target="studentAge", source = "student.age")
    @Mapping(target="studentAddressArea", source = "student.address.area")
    @Mapping(target="studentAddressStreet", source = "student.address.street", ignore = true)
    fun toFlatDto(school: School):FlatSchoolDto

    fun toDtoList(schools: List<School>):List<SchoolDto>

}