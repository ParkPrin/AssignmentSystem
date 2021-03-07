package me.parkprin.assignment.jparepository.user;

import me.parkprin.assignment.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
