package com.namankhurana.springboot.traini8.dao;

import com.namankhurana.springboot.traini8.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
}
