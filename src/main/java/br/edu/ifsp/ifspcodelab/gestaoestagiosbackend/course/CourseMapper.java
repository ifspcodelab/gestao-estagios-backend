package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDto to(Course course);
}
