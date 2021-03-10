package me.parkprin.assignment.security;

import me.parkprin.assignment.users.UserEntity;

public class JwtManager {

    public String newJwt(Jwt jwt, String[] roles, UserEntity userEntity) {
        Jwt.Claims claims = Jwt.Claims.of(userEntity.getUserSeq(), userEntity.getName(), roles);
        return jwt.create(claims);
    }
}
