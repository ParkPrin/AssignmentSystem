package me.parkprin.assignment.products;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long productSeq;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 1000)
    private String details;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int reviewCount;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    public void setName(String name) {
        nameValidation(name);
        this.name = name;
    }

    public void setDetails(String details) {
        detailsValidation(details);
        this.details = details;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", productSeq)
                .append("name", name)
                .append("details", details)
                .append("reviewCount", reviewCount)
                .append("createAt", createAt)
                .toString();
    }

    @Builder
    public ProductEntity(String name, String details){
        nameValidation(name);
        detailsValidation(details);
        this.name = name;
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        return new EqualsBuilder()
                .append(productSeq, that.productSeq)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(productSeq)
                .toHashCode();
    }

    private void nameValidation(String name){
        checkArgument(isNotEmpty(name), "name must be provided");
        checkArgument(
                name.length() >= 1 && name.length() <= 50,
                "name length must be between 1 and 50 characters"
        );
    }

    private void detailsValidation(String details){
        checkArgument(
                isEmpty(details) || details.length() <= 1000,
                "details length must be less than 1000 characters"
        );
    }


}
