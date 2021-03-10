package me.parkprin.assignment.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u.* FROM users u WHERE u.email = :email", nativeQuery = true)
    UserEntity findByEmail(@Param("email") String email);
}
