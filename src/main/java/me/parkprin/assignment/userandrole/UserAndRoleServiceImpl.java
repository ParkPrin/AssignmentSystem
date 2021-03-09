package me.parkprin.assignment.userandrole;

import me.parkprin.assignment.service.BasicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAndRoleServiceImpl implements BasicService<UserAndRoleEntity> {

    private final UserAndRoleJpaRepository userAndRoleJpaRepository;

    public UserAndRoleServiceImpl(UserAndRoleJpaRepository userAndRoleJpaRepository){
        this.userAndRoleJpaRepository = userAndRoleJpaRepository;
    }

    @Override
    public UserAndRoleEntity save(UserAndRoleEntity userAndRoleEntity) {
        return userAndRoleJpaRepository.save(userAndRoleEntity);
    }

    @Override
    public Long tableLength() {
        return userAndRoleJpaRepository.count();
    }

    @Override
    public void deleteAll() {
        userAndRoleJpaRepository.deleteAll();
    }

    @Override
    public List<UserAndRoleEntity> findAll() {
        return userAndRoleJpaRepository.findAll();
    }
}
