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
    @DisplayName("Lista vazia, Adição de novo currículo, Data de início e fim definidos, Data de início anterior a data de fim")
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

    //Uma lista com um ou mais currículos, todos com data de início e fim definidas e adição de um novo currículo com apenas data de início definida no mesmo dia de algum currículo já salvo, com sobreposição
    @Test
    @DisplayName("Lista não vazia com curículo com data de início e fim definidas, Adição de novo currículo, Apenas data de início definida, Sobreposição")
    void checkingAddCurriculumNotEmptyListStartDateEqualOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com um ou mais currículos, todos com data de início e fim definidas e adição de um novo currículo com apenas data de início definida no mesmo dia de algum currículo já salvo, com sobreposição
    @Test
    @DisplayName("Lista não vazia com curículo com data de início e fim definidas, Adição de novo currículo, Apenas data de início definida, Sobreposição de mesma Data")
    void checkingAddCurriculumNotEmptyListStartOrEndDateEqualOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN.plusMonths(12));
        curriculumSaved.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MIN.plusMonths(12));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com um ou mais currículos, um deles com apenas a data de início definida e adição de um novo currículo com data de início e fim definidas, data de fim no mesmo dia do currículo já salvo, com sobreposição
    @Test
    @DisplayName("Lista não vazia com curículo com apenas data de início definida, Adição de novo currículo, Data de início e fim definidas, Sobreposição de mesma data")
    void checkingAddCurriculumNotEmptyListEndDateSameOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN.plusMonths(12));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MIN.plusMonths(12));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com um ou mais currículos, todos com data de início e fim definidas e adição de um novo currículo com apenas data de início definida no mesmo dia de fim de um currículo já salvo, com sobreposição
    @Test
    @DisplayName("Lista não vazia com curículo com data de início e fim definidas, Adição de novo currículo, Apenas data de início definida, Sobreposição de mesma data")
    void checkingAddCurriculumNotEmptyListEndDateEqualOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MAX);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Lista vazia, Adição de um novo currículo com as datas de início e fim definidas para o mesmo dia
    @Test
    @DisplayName("Lista vazia, Adição de novo currículo, Datas definidas iguais, Sobreposição de mesma data")
    void checkingAddCurriculumEmptyListStartDateEqualsEndDate() {
        var curriculumList = new ArrayList<Curriculum>();
        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MAX);
        curriculum.setValidityEndDate(LocalDate.MAX);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(DateChronologyException.class);
    }

    //Uma lista com um ou mais currículos, todos com data de início e fim definidas e adição de um novo currículo com data de início e fim definidas, datas coincidentes, com sobreposição
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Adição de novo currículo, Data de início e fim definidos, Data de início coincidente, Sobreposição")
    void checkingAddCurriculumNotEmptyListSameDatesOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MIN);
        curriculumSaved.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN.plusMonths(12));
        curriculum.setValidityEndDate(LocalDate.MAX);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com currículo com apenas data de início definida, adição de um novo currículo com data de início e fim definidas, data de fim coincidente, com sobreposição
    @Test
    @DisplayName("Lista não vazia com curículo com apenas data de início definida, Adição de novo currículo, Data de início e fim definidas, Datas coincidentes, Sobreposição")
    void checkingAddCurriculumNotEmptyListEndDateEqualsOtherDateOverlay() {
        var curriculumSaved = new Curriculum();
        curriculumSaved.setValidityStartDate(LocalDate.MAX.minusMonths(12));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculumSaved);

        var curriculum = new Curriculum();
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MAX.minusMonths(12));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingAddCurriculum(curriculumList,curriculum))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com dois currículos: ambos com data de início e fim definidas; Atualização de um deles com data de início e fim definidas, sem sobreposição
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Atualização de um currículo, Data de início e fim definidos, Data de início anterior a data de fim")
    void checkingUpdateCurriculumAllDatesDefined() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MAX.minusMonths(12));
        curriculumUpdated.setValidityEndDate(LocalDate.MAX);

        Boolean result = curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId());

        assertThat(result).isTrue();
    }

    //Uma lista com dois currículos: ambos com data de início e fim definidas; Atualização de um deles com data de início e fim definidas, datas fora de ordem
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Atualização de um currículo, Data de início e fim definidos, Data de início depois da data de fim")
    void checkingUpdateCurriculumAllDatesDefinedStartAfterEnd() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MAX);
        curriculumUpdated.setValidityEndDate(LocalDate.MAX.minusMonths(12));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(DateChronologyException.class);
    }

    //Uma lista com dois currículos: ambos com data de início e fim definidas; Atualização de um deles com apenas data de início definida, sem sobreposição
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Atualização de um currículo, Apenas data de início")
    void checkingUpdateCurriculumAllDatesDefinedUpdateTOOnlyStartDate() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MAX.minusMonths(12));

        Boolean result = curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId());

        assertThat(result).isTrue();
    }

    //Uma lista com dois currículos: um com apenas data de início definida, outro com ambas datas definidas; Atualização do currículo com ambas as datas, sem sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Atualização de currículo, Datas de início e fim definidas")
    void checkingUpdateCurriculumUpdatingBothDates() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MAX);
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN);
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(10));
        curriculumUpdated.setValidityEndDate(LocalDate.MAX.minusDays(1));

        Boolean result = curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId());

        assertThat(result).isTrue();
    }

    //Uma lista com dois currículos: um com apenas data de início definida, outro com ambas datas definidas; Atualização do currículo com apenas data inicial, definindo ambas as datas; sem sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Atualização de currículo, Definindo ambas as datas")
    void checkingUpdateCurriculumUpdatingOnlyStartDateToBoth() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(15));
        curriculumUpdated.setValidityEndDate(LocalDate.MAX);

        Boolean result = curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId());

        assertThat(result).isTrue();
    }

    //Uma lista com dois currículos: um com apenas data de início definida, outro com ambas datas definidas; Atualização do currículo com apenas data inicial, atualizando a data de início; sem sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Atualização de currículo, Apenas data de início")
    void checkingUpdateCurriculumUpdatingOnlyStartDate() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MAX);

        Boolean result = curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId());

        assertThat(result).isTrue();
    }

    //Uma lista com dois currículos: ambos com data de início e fim definidas; Atualização de um deles com data de início e fim definidas, com sobreposição
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Atualização de um currículo, Data de início e fim definidos, Data de início anterior a data de fim, Sobreposição")
    void checkingUpdateCurriculumAllDatesDefinedOverlay() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(10));
        curriculumUpdated.setValidityEndDate(LocalDate.MAX);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com dois currículos: ambos com data de início e fim definidas; Atualização de um deles com data de início e fim definidas, mesma data
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Atualização de um currículo, Data de início e fim definidos, Data de início igual a data de fim")
    void checkingUpdateCurriculumAllDatesDefinedStartDateEqualsEndDate() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MAX);
        curriculumUpdated.setValidityEndDate(LocalDate.MAX);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(DateChronologyException.class);
    }

    //Uma lista com dois currículos: ambos com data de início e fim definidas; Atualização de um deles com apenas data de início definida, com sobreposição
    @Test
    @DisplayName("Lista não vazia com currículos com datas definidas, Atualização de um currículo, Apenas data de início, Sobreposição")
    void checkingUpdateCurriculumAllDatesDefinedUpdateTOOnlyStartDateOvelay() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com dois currículos: um com apenas data de início definida, outro com ambas datas definidas; Atualização do currículo com ambas as datas, com sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Atualização de currículo, Datas de início e fim definidas, Sobreposição")
    void checkingUpdateCurriculumUpdatingBothDatesOverlay() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MAX.minusDays(1));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN);
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(10));
        curriculumUpdated.setValidityEndDate(LocalDate.MAX);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com dois currículos: um com apenas data de início definida, outro com ambas datas definidas; Atualização do currículo com apenas data inicial, definindo ambas as datas; com sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Atualização de currículo, Definindo ambas as datas, Sobreposição")
    void checkingUpdateCurriculumUpdatingOnlyStartDateToBothOverlay() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(10));
        curriculumUpdated.setValidityEndDate(LocalDate.MIN.plusMonths(11));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com dois currículos: um com apenas data de início definida, outro com ambas datas definidas; Atualização do currículo com apenas data inicial, atualizando a data de início; com sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Atualização de currículo, Apenas data de início, Sobreposição")
    void checkingUpdateCurriculumUpdatingOnlyStartDateOverlay() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com dois currículos: um com apenas data de início definida, outro com ambas datas definidas; Atualização do currículo com ambas as datas, Datas coincidentes com sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Atualização de currículo, Datas de início e fim definidas, Datas coincidentes, Sobreposição")
    void checkingUpdateCurriculumUpdatingSameDatesOverlay() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MAX.minusDays(1));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN);
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(10));
        curriculumUpdated.setValidityEndDate(LocalDate.MAX.minusDays(1));

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(TimeOverlayException.class);
    }

    //Uma lista com dois currículos: um com apenas data de início definida, outro com ambas datas definidas; Atualização do currículo com apenas data inicial, definindo ambas as datas alguma coincidente; com sobreposição
    @Test
    @DisplayName("Lista não vazia com um curículo com apenas data de início definida, Atualização de currículo, Definindo ambas as datas, Datas coincidentes Sobreposição")
    void checkingUpdateCurriculumUpdatingOnlyStartDateToBothSameDateOverlay() {
        var curriculum = new Curriculum();
        curriculum.setId(UUID.fromString("32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f"));
        curriculum.setValidityStartDate(LocalDate.MIN);
        curriculum.setValidityEndDate(LocalDate.MIN.plusMonths(12));
        var curriculumToBeUpdated = new Curriculum();
        curriculumToBeUpdated.setId(UUID.fromString("30472217-b2eb-43dd-9d0c-1a2c536bb56d"));
        curriculumToBeUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12).plusDays(1));
        curriculumToBeUpdated.setValidityEndDate(LocalDate.MAX);
        var curriculumList = new ArrayList<Curriculum>();
        curriculumList.add(curriculum);
        curriculumList.add(curriculumToBeUpdated);
        var curriculumUpdated = new Curriculum();
        curriculumUpdated.setValidityStartDate(LocalDate.MIN.plusMonths(12));
        curriculumUpdated.setValidityEndDate(LocalDate.MAX);

        assertThatThrownBy(() -> curriculumOverlapVerification.checkingUpdateCurriculum(curriculumList,curriculumUpdated,curriculumToBeUpdated.getId()))
                .isInstanceOf(TimeOverlayException.class);
    }
}