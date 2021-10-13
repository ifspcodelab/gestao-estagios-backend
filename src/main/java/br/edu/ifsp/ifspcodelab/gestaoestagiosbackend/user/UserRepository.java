package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    @Query("select count(u) > 0 from User u where u.email = ?1 and u.id <> ?2")
    boolean existsByEmailExcludedId(String email, UUID id);
    boolean existsByRegistration(String registration);
    @Query("select count(u) > 0 from User u where u.registration = ?1 and u.id <> ?2")
    boolean existsByRegistrationExcludeId(String registration, UUID id);
    User findByRegistration(String registration);
}
