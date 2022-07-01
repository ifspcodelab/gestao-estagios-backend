package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CurriculumRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CurriculumRepository curriculumRepository;

    private Curriculum curriculum;
    private Course course;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        Campus campus = entityManager.persistAndFlush(CampusFactoryUtils.sampleCampus(city));
        Department department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        course = entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        curriculum = CurriculumFactoryUtils.sampleCurriculum(course);
    }

    @Test
    public void findByCourseIdAndStatus() {
        Curriculum curriculumEnabled0 = CurriculumFactoryUtils.sampleCurriculum(course);
        curriculumEnabled0.setStatus(EntityStatus.ENABLED);
        Curriculum curriculumDisabled = CurriculumFactoryUtils.sampleCurriculum(course);
        curriculumDisabled.setStatus(EntityStatus.DISABLED);
        Curriculum curriculumEnabled1 = CurriculumFactoryUtils.sampleCurriculum(course);
        curriculumEnabled1.setStatus(EntityStatus.ENABLED);

        entityManager.persistAndFlush(curriculumEnabled0);
        entityManager.persistAndFlush(curriculumDisabled);
        entityManager.persistAndFlush(curriculumEnabled1);

        List<Curriculum> curriculumList = curriculumRepository.findByCourseIdAndStatus(curriculum.getCourse().getId(), EntityStatus.ENABLED);

        assertThat(curriculumList).hasSize(2);
        assertThat(curriculumList).doesNotContain(curriculumDisabled);
        assertThat(curriculumList.get(0)).isEqualTo(curriculumEnabled0);
        assertThat(curriculumList.get(1)).isEqualTo(curriculumEnabled1);
    }

    @Test
    public void findAllByCourseIdWithCourseIdReturnsCurriculumListWithCourseId() {
        UUID courseId = course.getId();

        entityManager.persistAndFlush(curriculum);

        List<Curriculum> curriculumList = curriculumRepository.findAllByCourseId(courseId);

        assertThat(curriculumList)
                .isNotEmpty()
                .isInstanceOf(ArrayList.class)
                .hasSize(1);

        assertThat(curriculumList.get(0).getCourse().getId()).isEqualTo(courseId);
    }
    @Test
    public void findAllByCourseIdWithCourseIdNotInCurriculumCoursesReturnsEmptyList() {
        entityManager.persistAndFlush(curriculum);

        List<Curriculum> curriculumList = curriculumRepository.findAllByCourseId(UUID.randomUUID());

        assertThat(curriculumList)
                .isInstanceOf(ArrayList.class)
                .isEmpty();
    }

    @Test
    public void findAllByCourseIdWithoutCurriculumsReturnsEmptyList() {
        List<Curriculum> curriculumList = curriculumRepository.findAllByCourseId(UUID.randomUUID());

        assertThat(curriculumList)
                .isInstanceOf(ArrayList.class)
                .isEmpty();
    }
}