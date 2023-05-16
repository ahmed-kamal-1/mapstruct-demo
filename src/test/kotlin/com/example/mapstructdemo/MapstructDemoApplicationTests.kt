package com.example.mapstructdemo

import com.example.mapstructdemo.entity.School
import com.example.mapstructdemo.entity.Student
import com.example.mapstructdemo.entity.StudentAddress
import com.example.mapstructdemo.mapper.SchoolMapperInterface
import com.example.mapstructdemo.mapper.StudentMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MapstructDemoApplicationTests {

	private val entity = School(1,"school1",110,
		Student(1,"student1",10,
			StudentAddress(1,"area1","street1")))

	private val schoolMapperInterface: SchoolMapperInterface = Mappers.getMapper(SchoolMapperInterface::class.java)
	private val studentMapper: StudentMapper = Mappers.getMapper(StudentMapper::class.java)

	@Test
	fun basicCase() {

		val schoolDto = schoolMapperInterface.toDto(entity)
		assertEquals(entity.schoolName,schoolDto.name)
		assertEquals(entity.student.id,schoolDto.student.id)
		assertEquals(entity.student.address.area,schoolDto.student.address.area)
		assertNull(schoolDto.status)

		val school = schoolMapperInterface.toEntity(schoolDto)
		assertEquals(schoolDto.name,school.schoolName)
		assertEquals(schoolDto.student.id,school.student.id)
		assertEquals(schoolDto.student.address.area,school.student.address.area)
	}

	@Test
	fun generateFlatSchoolDto() {
		val flatSchoolDto = schoolMapperInterface.toFlatDto(entity)
		assertEquals(entity.schoolName,flatSchoolDto.schoolName)
		assertEquals(entity.student.name,flatSchoolDto.studentName)
		assertEquals(entity.student.address.area,flatSchoolDto.studentAddressArea)
		assertNull(flatSchoolDto.studentAddressStreet)

	}

	@Test
	fun generateSchoolDtoList() {
		val entityList = listOf(entity,
			School(2,"school2",110,
			Student(2,"student2",15,
				StudentAddress(2,"area2","street2"))))
		val schoolDtoList = schoolMapperInterface.toDtoList(entityList)
		assertEquals(entityList[0].schoolName,schoolDtoList[0].name)
		assertEquals(entityList[1].schoolName,schoolDtoList[1].name)

		assertEquals(entityList[0].student.id,schoolDtoList[0].student.id)
		assertEquals(entityList[1].student.id,schoolDtoList[1].student.id)

		assertEquals(entityList[0].student.address.area,schoolDtoList[0].student.address.area)
		assertEquals(entityList[1].student.address.area,schoolDtoList[1].student.address.area)

	}

	@Test
	fun generateStudentDto() {
		val studentDto = studentMapper.toDto(entity.student)

		assertEquals(entity.student.id,studentDto.id)
		assertEquals(entity.student.address.area,studentDto.address.area)
	}

}
