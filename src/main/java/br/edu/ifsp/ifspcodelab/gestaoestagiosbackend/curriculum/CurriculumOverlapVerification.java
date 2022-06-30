package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DateChronologyException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.TimeOverlayException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CurriculumOverlapVerification {

    public boolean checkingAddCurriculum(List<Curriculum> curriculums, Curriculum curriculum){
        var validityStartDate = curriculum.getValidityStartDate();
        var validityEndDate = curriculum.getValidityEndDate();
        if(!(validityEndDate == null)) {
            if (validityStartDate.isAfter(validityEndDate)) {
                throw new DateChronologyException(validityStartDate, validityEndDate);
            }
            for(Curriculum c : curriculums){
                if(!(c.getValidityEndDate() == null) && (
                        (validityStartDate.isAfter(c.getValidityStartDate()) && validityStartDate.isBefore(c.getValidityEndDate())) || (validityEndDate.isAfter(c.getValidityStartDate()) && validityEndDate.isBefore(c.getValidityEndDate())))
                ){
                    throw new TimeOverlayException(validityStartDate,validityEndDate,c.getValidityStartDate(),c.getValidityEndDate());
                }
                else if(
                        c.getValidityEndDate() == null && (validityStartDate.isAfter(c.getValidityStartDate()) || validityEndDate.isAfter(c.getValidityStartDate()))
                ){
                    throw new TimeOverlayException(validityStartDate,validityEndDate,c.getValidityStartDate(),c.getValidityEndDate());
                }
            }
        }
        else if(!(curriculums.isEmpty())){
           for(Curriculum c : curriculums){
               if(c.getValidityEndDate() == null){
                   throw new TimeOverlayException(validityStartDate, LocalDate.now(),c.getValidityStartDate(),LocalDate.now());
               }
               if(
                       (validityStartDate.isAfter(c.getValidityStartDate()) && validityStartDate.isBefore(c.getValidityEndDate())) || validityStartDate.isBefore(c.getValidityStartDate())
               ){
                   throw new TimeOverlayException(validityStartDate,validityEndDate,c.getValidityStartDate(),c.getValidityEndDate());
               }
           }
        }
        return true;
    }


}
