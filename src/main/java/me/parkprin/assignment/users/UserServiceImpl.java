package me.parkprin.assignment.users;

import me.parkprin.assignment.reviews.ReviewEntity;
import me.parkprin.assignment.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements BasicService<UserEntity> {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public UserEntity save(UserEntity user){
        return userJpaRepository.save(user);
    }
    public Long tableLength(){
        return userJpaRepository.count();
    }
    public void deleteAll(){
        userJpaRepository.deleteAll();
    }
    public List<UserEntity> findAll(){
        return userJpaRepository.findAll();
    }
}
