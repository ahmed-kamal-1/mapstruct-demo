package com.example.mapstructdemo.mapper

import com.example.mapstructdemo.dto.SchoolDto
import com.example.mapstructdemo.entity.School
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper
abstract class SchoolMapperAbstractClass {

    private val studentMapper: StudentMapper = Mappers.getMapper(StudentMapper::class.java)


    @Mapping(target="name", source = "schoolName")
    abstract fun toDto(school: School): SchoolDto

    @Mapping(target="schoolName", source = "name")
    abstract fun toEntity(schoolDto: SchoolDto): School

    fun toDtoAndDefineStatus(school: School): SchoolDto{
        val schoolStatus = if(school.studentsCount>100) {
            "FullCapacity"
        }else{
            "open"
        }
       return SchoolDto(
            id= school.id,
            name = school.schoolName,
            status =schoolStatus,
            student = studentMapper.toDto(school.student)
        )
    }


    // how @AfterMapping know which method it would extend , simply just by the @MappingTarget type so in this example
    // any method that returns SchoolDto as a return type it would be extended by this method annotated by @AfterMapping
    @AfterMapping
    fun makeNameUpperCase(@MappingTarget schoolDto: SchoolDto){
        schoolDto.name.let { schoolDto.name= it.uppercase() }
    }


    @AfterMapping
    fun explicitlyDefineStatus(school: School,@MappingTarget schoolDto: SchoolDto){
        schoolDto.status = if(school.studentsCount>100) {
            "FullCapacity"
        }else{
            "open"
        }
    }

}