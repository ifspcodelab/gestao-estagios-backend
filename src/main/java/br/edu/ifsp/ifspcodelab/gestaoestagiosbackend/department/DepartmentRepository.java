package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, UUID> {
}
