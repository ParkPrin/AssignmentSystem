package me.parkprin.assignment.reviews;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.parkprin.assignment.products.ProductEntity;
import me.parkprin.assignment.users.UserEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long reviewSeq;

    @ManyToOne
    @JoinColumn(name = "userSeq")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private ProductEntity product;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    public void setUserSeq(UserEntity user) {
        userSeqValidation(user);
        this.user = user;
    }

    public void setProductSeq(ProductEntity product) {
        productSeqValidation(product);
        this.product = product;
    }

    public void setContent(String content) {
        contentValidation(content);
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ReviewEntity that = (ReviewEntity) o;

        return new EqualsBuilder()
                .append(reviewSeq, that.reviewSeq)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(reviewSeq)
                .toHashCode();
    }

    @Builder
    public ReviewEntity(UserEntity user, ProductEntity product, String content){
        userSeqValidation(user);
        productSeqValidation(product);
        contentValidation(content);
        this.user = user;
        this.product = product;
        this.content = content;
    }

    private void userSeqValidation(UserEntity user){
        checkArgument(user == null ? true: false, "userSeq must be provided");
    }

    private void productSeqValidation(ProductEntity productSeq){
        checkArgument(productSeq == null ? true: false, "productSeq must be provided");
    }

    private void contentValidation(String content){
        checkArgument(isNotEmpty(content), "content must be provided");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", reviewSeq)
                .append("product", product)
                .append("content", content)
                .append("createAt", createAt)
                .toString();
    }








}
