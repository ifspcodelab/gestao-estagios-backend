package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {
    boolean existsByEmail(String email);
}
