package me.parkprin.assignment.products;

import me.parkprin.assignment.products.ProductEntity;
import me.parkprin.assignment.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT p.* FROM products p ORDER BY seq DESC", nativeQuery = true)
    List<ProductEntity> findAllDesc ();
}
