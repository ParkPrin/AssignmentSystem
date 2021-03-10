package me.parkprin.assignment.userandrole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAndRoleJpaRepository extends JpaRepository<UserAndRoleEntity, Long> {
    @Query(value = "SELECT uar.* FROM usersandroles uar WHERE uar.user_seq = :userSeq", nativeQuery = true)
    List<UserAndRoleEntity> findByUserSeq(@Param("userSeq") Long userSeq);

    @Query(value = "SELECT uar.* FROM usersandroles uar WHERE uar.role_seq = :roleSeq", nativeQuery = true)
    List<UserAndRoleEntity> findByRoleSeq(@Param("roleSeq") Long roleSeq);
}
