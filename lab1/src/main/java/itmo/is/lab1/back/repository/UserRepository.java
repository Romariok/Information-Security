package itmo.is.lab1.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itmo.is.lab1.back.model.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);

   boolean existsByUsername(String username);

}