package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface CampusRepository extends PagingAndSortingRepository <Campus, UUID> {
    boolean  existsCampusByAbbreviation(String abbreviation);

}

