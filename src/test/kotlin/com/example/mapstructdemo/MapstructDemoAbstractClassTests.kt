package com.example.mapstructdemo

import com.example.mapstructdemo.entity.School
import com.example.mapstructdemo.entity.Student
import com.example.mapstructdemo.entity.StudentAddress
import com.example.mapstructdemo.mapper.SchoolMapperAbstractClass
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MapstructDemoAbstractClassTests {

	private val entity = School(1,"school1",90,
		Student(1,"student1",10,
			StudentAddress(1,"area1","street1")))

	private val mapperAbstractClass: SchoolMapperAbstractClass = Mappers.getMapper(SchoolMapperAbstractClass::class.java)



	@Test
	fun basicCase() {
		val schoolDto = mapperAbstractClass.toDto(entity)
		Assertions.assertEquals(entity.student.id, schoolDto.student.id)
		Assertions.assertEquals(entity.student.address.area, schoolDto.student.address.area)

		Assertions.assertEquals(entity.schoolName, schoolDto.name)
		Assertions.assertNull(schoolDto.status)

//		Assertions.assertNotNull(schoolDto.status)
//		Assertions.assertEquals(entity.schoolName.uppercase(), schoolDto.name)

		val school = mapperAbstractClass.toEntity(schoolDto)
		Assertions.assertEquals(schoolDto.name, school.schoolName)
		Assertions.assertEquals(schoolDto.student.id, school.student.id)
		Assertions.assertEquals(schoolDto.student.address.area, school.student.address.area)
	}


	@Test
	fun generateSchoolDtoWithStatus() {
		entity.studentsCount = 10
		var schoolDtoWithStatus = mapperAbstractClass.toDtoAndDefineStatus(entity)
		Assertions.assertEquals("open", schoolDtoWithStatus.status)

		entity.studentsCount = 110
		schoolDtoWithStatus = mapperAbstractClass.toDtoAndDefineStatus(entity)
		Assertions.assertEquals("FullCapacity", schoolDtoWithStatus.status)

	}

}
