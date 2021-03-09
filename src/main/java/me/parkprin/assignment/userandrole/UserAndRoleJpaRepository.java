package me.parkprin.assignment.userandrole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAndRoleJpaRepository extends JpaRepository<UserAndRoleEntity, Long> {
}
