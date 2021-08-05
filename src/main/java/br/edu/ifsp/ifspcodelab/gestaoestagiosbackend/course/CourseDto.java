package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import lombok.Value;

import java.util.UUID;

@Value
public class CourseDto {
    UUID id;
    String name;
    String abbreviation;
    Integer numberOfPeriods;
}
