package me.parkprin.assignment.role;

import me.parkprin.assignment.service.BasicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements BasicService<RoleEntity> {

    private final RoleJpaRepository roleJpaRepository;

    public RoleServiceImpl(RoleJpaRepository roleJpaRepository){
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return roleJpaRepository.save(roleEntity);
    }

    @Override
    public Long tableLength() {
        return roleJpaRepository.count();
    }

    @Override
    public void deleteAll() {
        roleJpaRepository.deleteAll();
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleJpaRepository.findAll();
    }
}
