package me.parkprin.assignment.role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.parkprin.assignment.orders.OrderStatus;
import me.parkprin.assignment.userandrole.UserAndRoleEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_SEQ")
    private Long roleSeq;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    private RoleStatus status;

    @Builder
    public RoleEntity(String name, RoleStatus status){
        checkArgument(isNotEmpty(name), "name must be provided");
        checkArgument(
                name.length() >= 1 && name.length() <= 30,
                "name length must be between 1 and 30 characters"
        );
        checkArgument(status!=null?true:false, "roleStatus must be provided");
        this.name = name;
        this.status =status;
        this.createAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        return new EqualsBuilder()
                .append(roleSeq, that.roleSeq)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(roleSeq)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", roleSeq)
                .append("name", name)
                .append("createAt", createAt)
                .toString();
    }
}
