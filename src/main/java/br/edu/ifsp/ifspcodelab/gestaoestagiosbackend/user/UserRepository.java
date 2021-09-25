package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    boolean existsByRegistration(String registration);
    User findByRegistration(String registration);
}
