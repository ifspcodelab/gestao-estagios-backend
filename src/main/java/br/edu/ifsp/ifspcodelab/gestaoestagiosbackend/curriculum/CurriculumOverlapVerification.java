package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DateChronologyException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.TimeOverlayException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CurriculumOverlapVerification {

    public boolean checkingAddCurriculum(List<Curriculum> curriculums, CurriculumCreateDto curriculum){
        var validityStartDate = curriculum.getValidityStartDate();
        var validityEndDate = curriculum.getValidityEndDate();
        if(validityEndDate != null) {
            verifyDatesChronology(validityStartDate, validityEndDate);
            for(Curriculum c : curriculums){
                var comparingStartDate = c.getValidityStartDate();
                var comparingEndDate = c.getValidityEndDate();
                if(comparingEndDate != null && (
                        (validityStartDate.isAfter(comparingStartDate) && validityStartDate.isBefore(comparingEndDate)) || (validityEndDate.isAfter(comparingStartDate) && validityEndDate.isBefore(comparingEndDate)) || validityStartDate.isEqual(comparingStartDate) || validityStartDate.isEqual(comparingEndDate) || validityEndDate.isEqual(comparingStartDate) || validityEndDate.isEqual(comparingEndDate))
                ){
                    throw new TimeOverlayException(validityStartDate,validityEndDate,comparingStartDate,comparingEndDate);
                }
                else if(
                        comparingEndDate == null && (validityStartDate.isAfter(comparingStartDate) || validityEndDate.isAfter(comparingStartDate) || validityStartDate.isEqual(comparingStartDate) || validityEndDate.isEqual(comparingStartDate))
                ){
                    throw new TimeOverlayException(validityStartDate,validityEndDate,comparingStartDate,LocalDate.now());
                }
            }
        }
        else if(!(curriculums.isEmpty())){
           for(Curriculum c : curriculums){
               var comparingStartDate = c.getValidityStartDate();
               var comparingEndDate = c.getValidityEndDate();
               if(comparingEndDate == null){
                   throw new TimeOverlayException(validityStartDate, LocalDate.now(),comparingStartDate,LocalDate.now());
               }
               if(
                       validityStartDate.isBefore(comparingStartDate) || validityStartDate.isBefore(comparingEndDate) || validityStartDate.isEqual(comparingStartDate) || validityStartDate.isEqual(comparingEndDate)
               ){
                   throw new TimeOverlayException(validityStartDate,validityEndDate,comparingStartDate,comparingEndDate);
               }
           }
        }
        return true;
    }

    public boolean checkingUpdateCurriculum(List<Curriculum> curriculums, CurriculumCreateDto curriculum, UUID curriculumID){
        var validityStartDate = curriculum.getValidityStartDate();
        var validityEndDate = curriculum.getValidityEndDate();
        if(validityEndDate != null) {
            verifyDatesChronology(validityStartDate, validityEndDate);
            for(Curriculum c : curriculums){
                var comparingStartDate = c.getValidityStartDate();
                var comparingEndDate = c.getValidityEndDate();
                if(
                        (comparingEndDate != null && c.getId() != curriculumID) && ((validityStartDate.isAfter(comparingStartDate) && validityStartDate.isBefore(comparingEndDate)) || (validityEndDate.isAfter(comparingStartDate) && validityEndDate.isBefore(comparingEndDate)) || validityStartDate.isEqual(comparingStartDate) || validityStartDate.isEqual(comparingEndDate) || validityEndDate.isEqual(comparingStartDate) || validityEndDate.isEqual(comparingEndDate))
                ){
                    throw  new TimeOverlayException(validityStartDate, validityEndDate, comparingStartDate, comparingEndDate);
                }
                else if (
                        (comparingEndDate == null && c.getId() != curriculumID) && (validityStartDate.isAfter(comparingStartDate) || validityEndDate.isAfter(comparingStartDate) || validityStartDate.isEqual(comparingStartDate) || validityEndDate.isEqual(comparingStartDate))
                ){
                    throw new TimeOverlayException(validityStartDate, validityEndDate,comparingStartDate,LocalDate.now());
                }
            }
        }
        else {
            for(Curriculum c : curriculums){
                var comparingStartDate = c.getValidityStartDate();
                var comparingEndDate = c.getValidityEndDate();
                if(
                        c.getId() != curriculumID && (validityStartDate.isBefore(comparingStartDate) || validityStartDate.isBefore(comparingEndDate) || validityStartDate.isEqual(comparingStartDate) || validityStartDate.isEqual(comparingEndDate))
                ){
                    throw new TimeOverlayException(validityStartDate,LocalDate.now(),comparingStartDate,comparingEndDate);
                }
            }
        }
        return true;
    }

    private void verifyDatesChronology(LocalDate startDate, LocalDate endDate){
        if(startDate.isAfter(endDate) || startDate.isEqual(endDate)){
            throw new DateChronologyException(startDate, endDate);
        }
    }
}
