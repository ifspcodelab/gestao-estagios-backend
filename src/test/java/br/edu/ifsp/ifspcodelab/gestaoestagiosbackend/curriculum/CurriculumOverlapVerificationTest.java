package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DateChronologyException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.TimeOverlayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class CurriculumOverlapVerificationTest {
    private CurriculumOverlapVerification curriculumOverlapVerification;

    @BeforeEach
    void setUp(){
        curriculumOverlapVerification = new CurriculumOverlapVerification();
    }


    //Uma lista vazia e adição de um novo currículo com data de início e fim definidas
    @Test
    @DisplayName("Lista vazia, Adição de novo currículo, Data de início e fim definidos, Aata de início anterior a data de fim")
    void checkingAddCurriculumEmptyList() {
        var curriculumList = new ArrayList<Curriculum>();
        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MAX);

        Boolean result = curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Lista vazia, Adição de novo currículo, Data de início e fim definidos, Data de início depois da data de fim")
    void checkingAddCurriculumEmptyWithStartDateAfterEndDate() {
        var curriculumList = new ArrayList<Curriculum>();
        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MAX);
        curriculum.setValidityEndDate(LocalDate.MIN);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(DateChronologyException.class);
    }

    //Uma lista vazia e adição de um novo currículo com apenas data de início definida
    @Test
    @DisplayName("Lista vazia, Adição de novo currículo, Apenas data de início definida")
    void checkingAddCurriculumEmptyListOnlyStartDateDefinied() {
        var curriculumList = new ArrayList<Curriculum>();
        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN);

        Boolean result = curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum);

        assertThat(result).isTrue();
    }

    //Uma lista com um ou mais currículos, todos com data de início e fim definidas e adição de um novo currículo com data de início e fim definidas, sem sobreposição
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Adição de novo currículo, Data de início e fim definidos, Data de início anterior a data de fim")
    void checkingAddCurriculumNotEmptyList() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MAX.minusMonths(12));
        curriculum.setValidityEndDate(LocalDate.MAX);

        Boolean result = curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum);

        assertThat(result).isTrue();
    }

    //Uma lista com um ou mais currículos, todos com data de início e fim definidas e adição de um novo currículo com apenas data de início definida, sem sobreposição
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Adição de novo currículo, Apenas data de início")
    void checkingAddCurriculumNotEmptyListOnlyStartDateDefined() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MAX.minusMonths(12));

        Boolean result = curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum);

        assertThat(result).isTrue();
    }

    //Uma lista com currículo com apenas data de início definida, adição de um novo currículo com data de início e fim definidas, sem sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Adição de novo currículo, Datas de início e fim definidas")
    void checkingAddCurriculumNotEmptyListOneWithOnlyStartDateDefined() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));

        Boolean result = curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum);

        assertThat(result).isTrue();
    }

    //Uma lista com um ou mais currículos, um deles com apenas data de início definida, os outros com ambas as datas definidas, e adição de um novo currículo com apenas data de início definida
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Adição de novo currículo, Apenas data de início definida")
    void checkingAddCurriculumNotEmptyListOneWithOnlyStartDateDefinedOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com um ou mais currículos, todos com data de início e fim definidas e adição de um novo currículo com data de início e fim definidas, com sobreposição
    @Test
    @DisplayName("Lista não vazia com curículo com data de início e fim definidas, Adição de novo currículo, Datas de início e fim definidas, Sobreposição")
    void checkingAddCurriculumNotEmptyListDatesDefinedOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN.plusMonths(12));
        curriculum.setValidityEndDate(LocalDate.MAX.minusMonths(12));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com um ou mais currículos, todos com data de início e fim definidas e adição de um novo currículo com apenas data de início definida, com sobreposição
    @Test
    @DisplayName("Lista não vazia com curículo com data de início e fim definidas, Adição de novo currículo, Apenas data de início definida, Sobreposição")
    void checkingAddCurriculumNotEmptyListOnlyStartDateDefinedOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN.plusMonths(12));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com currículo com apenas data de início definida, adição de um novo currículo com data de início e fim definidas, com sobreposição
    @Test
    @DisplayName("Lista não vazia com curículo com apenas data de início definida, Adição de novo currículo, Data de início e fim definidas, Sobreposição")
    void checkingAddCurriculumNotEmptyListOneWithOnlySartDateDefinedOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN.plusMonths(12));
        curriculum.setValidityEndDate(LocalDate.MAX);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }



}