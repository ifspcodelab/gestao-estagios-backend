package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.StateFactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CurriculumRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CurriculumRepository curriculumRepository;

    private Course course;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        Campus campus = entityManager.persistAndFlush(CampusFactoryUtils.sampleCampus(city));
        Department department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        course = entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
    }

    @Test
    public void findAllByCourseIdWithCurriculumsWithCourseIdReturnsListOfCurriculums() {
        Curriculum curriculum1 = CurriculumFactoryUtils.sampleCurriculum(course);
        Curriculum curriculum2 = CurriculumFactoryUtils.sampleCurriculum(course);
        entityManager.persistAndFlush(course);
        entityManager.persistAndFlush(curriculum1);
        entityManager.persistAndFlush(curriculum2);

        List<Curriculum> curriculumList = curriculumRepository.findAllByCourseId(course.getId());

        assertThat(curriculumList)
                .hasSize(2)
                .extracting(Curriculum::getCourse)
                .containsExactlyInAnyOrder(course, course);
    }

    @Test
    public void findAllByCourseIdWithNewCourseIdReturnsEmptyList() {
        entityManager.persistAndFlush(CurriculumFactoryUtils.sampleCurriculum(course));
        UUID newCourseId = UUID.randomUUID();

        List<Curriculum> curriculumList = curriculumRepository.findAllByCourseId(newCourseId);

        assertThat(curriculumList)
                .isEmpty();
    }

    @Test
    public void findAllByCourseIdWithoutPersistedCurriculumsReturnsEmptyList() {
        List<Curriculum> curriculumList = curriculumRepository.findAllByCourseId(UUID.randomUUID());

        assertThat(curriculumList)
                .isEmpty();
    }
}