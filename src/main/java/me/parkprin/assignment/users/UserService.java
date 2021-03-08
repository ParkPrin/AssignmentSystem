package me.parkprin.assignment.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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
}
