package me.parkprin.assignment.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static java.util.regex.Pattern.matches;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 80)
    private String passwd;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int loginCount;

    @Column(nullable = true)
    private LocalDateTime lastLoginAt;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    public void setPassword(String passwd) {
        this.passwd = passwd;
    }

    public void afterLoginSuccess() {
        loginCount++;
        lastLoginAt = now();
    }

    @Builder
    public UserEntity(String name, String email, String password, int loginCount){
        createUserValidation(name, email, passwd, loginCount);
        emailCheck(email);
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.loginCount = loginCount;
        this.lastLoginAt = null;
        this.createAt = now();
    }

    private void createUserValidation(String name, String email, String password, int loginCount){
        checkArgument(isNotEmpty(name), "name must be provided");
        checkArgument(
                name.length() >= 1 && name.length() <= 10,
                "name length must be between 1 and 10 characters"
        );
        checkNotNull(email, "email must be provided");
        checkNotNull(password, "password must be provided");
    }

    private void emailCheck(String email){
        checkArgument(isNotEmpty(email), "address must be provided");
        checkArgument(
                email.length() >= 4 && email.length() <= 50,
                "address length must be between 4 and 50 characters"
        );
        checkArgument(checkAddress(email), "Invalid email address: " + email);
    }

    private static boolean checkAddress(String address) {
        return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return loginCount == that.loginCount &&
                Objects.equals(seq, that.seq) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(passwd, that.passwd) &&
                Objects.equals(lastLoginAt, that.lastLoginAt) &&
                Objects.equals(createAt, that.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, name, email, passwd, loginCount, lastLoginAt, createAt);
    }
}
