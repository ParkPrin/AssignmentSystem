package me.parkprin.assignment.users;

import me.parkprin.assignment.errors.NotFoundException;
import me.parkprin.assignment.reviews.ReviewEntity;
import me.parkprin.assignment.service.BasicService;
import me.parkprin.assignment.userandrole.UserAndRoleEntity;
import me.parkprin.assignment.userandrole.UserAndRoleServiceImpl;
import me.parkprin.assignment.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements BasicService<UserEntity> {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder){
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserEntity save(UserEntity user){
        return userJpaRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Long tableLength(){
        return userJpaRepository.count();
    }

    @Transactional
    public void deleteAll(){
        userJpaRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAll(){
        return userJpaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserEntity findByEmail(String email) {
        checkNotNull(email, "email must be provided");
        ValidationUtils.emailValidation(email);

        return userJpaRepository.findByEmail(email);
    }

    @Transactional
    public UserEntity login(String email, String password){
        checkNotNull(password, "password must be provided");
        UserEntity userEntity = findByEmail(email);
        Optional<UserEntity> optionalUserEntity = ofNullable(userEntity==null ? null :userEntity);
        userEntity = optionalUserEntity
                .orElseThrow(() -> new NotFoundException("Could not found user for " + email));
        loginPasswordCheck(passwordEncoder, password, userEntity);
        userEntity.afterLoginSuccess();
        save(userEntity);
        return userEntity;

    }

    private void loginPasswordCheck(PasswordEncoder passwordEncoder, String credentials, UserEntity userEntity) {
        if (!passwordEncoder.matches(credentials, userEntity.getPasswd())) {
            throw new IllegalArgumentException("Bad credential");
        }
    }
}
