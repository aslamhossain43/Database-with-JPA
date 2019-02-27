package com.renu.jpa.many_to_many.models;

import java.util.Set;

public class TeacherStudentCombined {
private Set<Teacher>teachers;
private Set<Student>students;
public TeacherStudentCombined() {}
public Set<Teacher> getTeachers() {
	return teachers;
}
public void setTeachers(Set<Teacher> teachers) {
	this.teachers = teachers;
}
public Set<Student> getStudents() {
	return students;
}
public void setStudents(Set<Student> students) {
	this.students = students;
}


}
