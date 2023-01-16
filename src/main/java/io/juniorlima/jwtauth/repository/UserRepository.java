package io.juniorlima.jwtauth.repository;

import io.juniorlima.jwtauth.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByEmail(final String email);
}
