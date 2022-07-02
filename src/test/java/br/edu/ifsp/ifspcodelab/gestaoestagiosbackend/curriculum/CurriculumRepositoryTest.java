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
    public void findByCourseIdAndStatusReturnsOnlyEnabledCourses() {
        Curriculum curriculumEnabled0 = CurriculumFactoryUtils.sampleCurriculum(course);
        Curriculum curriculumEnabled1 = CurriculumFactoryUtils.sampleCurriculum(course);
        Curriculum curriculumDisabled = CurriculumFactoryUtils.sampleCurriculum(course);
        curriculumDisabled.setStatus(EntityStatus.DISABLED);
        entityManager.persistAndFlush(curriculumEnabled0);
        entityManager.persistAndFlush(curriculumEnabled1);
        entityManager.persistAndFlush(curriculumDisabled);

        List<Curriculum> curriculumList = curriculumRepository.findByCourseIdAndStatus(curriculum.getCourse().getId(), EntityStatus.ENABLED);

        assertThat(curriculumList)
                .hasSize(2)
                .doesNotContain(curriculumDisabled);
        assertThat(curriculumList.get(0)).isEqualTo(curriculumEnabled0);
        assertThat(curriculumList.get(1)).isEqualTo(curriculumEnabled1);
    }

    @Test
    public void findByCourseIdAndStatusReturnsOnlyDisabledCourses() {
        Curriculum curriculumEnabled = CurriculumFactoryUtils.sampleCurriculum(course);
        Curriculum curriculumDisabled0 = CurriculumFactoryUtils.sampleCurriculum(course);
        Curriculum curriculumDisabled1 = CurriculumFactoryUtils.sampleCurriculum(course);
        curriculumDisabled0.setStatus(EntityStatus.DISABLED);
        curriculumDisabled1.setStatus(EntityStatus.DISABLED);
        entityManager.persistAndFlush(curriculumEnabled);
        entityManager.persistAndFlush(curriculumDisabled0);
        entityManager.persistAndFlush(curriculumDisabled1);

        List<Curriculum> curriculumList = curriculumRepository.findByCourseIdAndStatus(curriculum.getCourse().getId(), EntityStatus.DISABLED);

        assertThat(curriculumList)
                .hasSize(2)
                .doesNotContain(curriculumEnabled);
        assertThat(curriculumList.get(0)).isEqualTo(curriculumDisabled0);
        assertThat(curriculumList.get(1)).isEqualTo(curriculumDisabled1);
    }

    @Test
    public void existsByCourseId()
    {
        entityManager.persistAndFlush(curriculum);

        boolean exists = curriculumRepository.existsByCourseId(curriculum.getCourse().getId());

        assertThat(exists).isTrue();
    }

    @Test
    public void notExistsByCourseId()
    {
        entityManager.persistAndFlush(curriculum);

        boolean exists = curriculumRepository.existsByCourseId(UUID.randomUUID());

        assertThat(exists).isFalse();
    }

    @Test
    public void disableAllByCourseId()
    {
        Curriculum curriculum0 = CurriculumFactoryUtils.sampleCurriculum(course);
        Curriculum curriculum1 = CurriculumFactoryUtils.sampleCurriculum(course);
        entityManager.persistAndFlush(curriculum0);
        entityManager.persistAndFlush(curriculum1);

        curriculumRepository.disableAllByCourseId(curriculum.getCourse().getId());

        assertThat(curriculum0.getStatus().equals(EntityStatus.DISABLED));
        assertThat(curriculum1.getStatus().equals(EntityStatus.DISABLED));
    }

    @Test
    public void disableAllByCourseIdDoesNotDisableCurriculumsFromOtherCourses()
    {
        Curriculum curriculum0 = CurriculumFactoryUtils.sampleCurriculum(course);
        Curriculum curriculum1 = CurriculumFactoryUtils.sampleCurriculum(course);
        entityManager.persistAndFlush(curriculum0);
        entityManager.persistAndFlush(curriculum1);

        curriculumRepository.disableAllByCourseId(UUID.randomUUID());

        assertThat(curriculum0.getStatus().equals(EntityStatus.ENABLED));
        assertThat(curriculum1.getStatus().equals(EntityStatus.ENABLED));
    }
}
