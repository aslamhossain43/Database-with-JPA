package com.renu.jpa.many_to_many.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Teacher extends TimeEntity<Long> {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String name;
@ManyToMany(cascade=CascadeType.ALL)
private Set<Student>students;
public Teacher() {}

@Override
public Long getId() {
	return id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Set<Student> getStudents() {
	return students;
}
public void setStudents(Set<Student> students) {
	this.students = students;
}
public void setId(Long id) {
	this.id = id;
}


}
