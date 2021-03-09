package me.parkprin.assignment.userandrole;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.parkprin.assignment.role.RoleEntity;
import me.parkprin.assignment.users.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "usersandroles")
public class UserAndRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long userAndRoleSeq;

    @ManyToOne
    @JoinColumn(name = "userSeq")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "roleSeq")
    private RoleEntity role;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    @Builder
    public UserAndRoleEntity(UserEntity user, RoleEntity role){
        this.user = user;
        this.role = role;
        this.createAt = LocalDateTime.now();
    }


}
