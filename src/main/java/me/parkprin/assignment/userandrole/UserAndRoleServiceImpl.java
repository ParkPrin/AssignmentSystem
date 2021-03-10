package me.parkprin.assignment.userandrole;

import me.parkprin.assignment.service.BasicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @Override
    public Long tableLength() {
        return userAndRoleJpaRepository.count();
    }

    @Override
    public void deleteAll() {
        userAndRoleJpaRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserAndRoleEntity> findAll() {
        return userAndRoleJpaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<UserAndRoleEntity> findByUserSeq(Long userSeq){
        return userAndRoleJpaRepository.findByUserSeq(userSeq);
    }

    @Transactional(readOnly = true)
    public List<UserAndRoleEntity> findByRoleSeq(Long roleSeq){
        return userAndRoleJpaRepository.findByRoleSeq(roleSeq);
    }
}
