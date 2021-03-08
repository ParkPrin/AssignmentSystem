package me.parkprin.assignment.orders;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.parkprin.assignment.products.ProductEntity;
import me.parkprin.assignment.reviews.ReviewEntity;
import me.parkprin.assignment.users.UserEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long orderSeq;

    @ManyToOne
    @JoinColumn(name = "userSeq")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "productSeq")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "reviewSeq")
    private ReviewEntity review;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = true, length = 1000)
    private String requestMsg;

    @Column(nullable = true, length = 1000)
    private String rejectMsg;

    @Column(nullable = true)
    private LocalDateTime completedAt;

    @Column(nullable = true)
    private LocalDateTime rejectedAt;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    public void setUser(UserEntity user) {
        userSeqValidation(user);
        this.user = user;
    }

    public void setProduct(ProductEntity product) {
        productSeqValidation(product);
        this.product = product;
    }

    public void setReview(ReviewEntity review) {
        checkArgument(review != null ? true: false, "review must be provided");
        this.review = review;
    }

    public void setStatus(OrderStatus status) {
        orderStatusValidation(status);
        this.status = status;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public void setRejectMsg(String rejectMsg) {
        this.rejectMsg = rejectMsg;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setRejectAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", orderSeq)
                .append("productId", product)
                .append("review", review)
                .append("state", status)
                .append("requestMessage", requestMsg)
                .append("rejectMessage", rejectMsg)
                .append("completedAt", completedAt)
                .append("rejectedAt", rejectedAt)
                .append("createAt", createAt)
                .toString();
    }

    @Builder
    public OrderEntity(UserEntity user, ProductEntity product, ReviewEntity review, OrderStatus status,
                       String requestMsg, String rejectMsg, LocalDateTime completedAt, LocalDateTime rejectedAt) {
        userSeqValidation(user);
        productSeqValidation(product);
        orderStatusValidation(status);
        this.user = user;
        this.product = product;
        this.review = review;
        this.status = status;
        this.requestMsg = requestMsg;
        this.rejectMsg = rejectMsg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        return new EqualsBuilder()
                .append(orderSeq, that.orderSeq)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(orderSeq)
                .toHashCode();
    }

    private void userSeqValidation(UserEntity user){
        checkArgument(user != null ? true: false, "user must be provided");
    }

    private void productSeqValidation(ProductEntity product){
        checkArgument(product != null ? true: false, "product must be provided");
    }

    private void orderStatusValidation(OrderStatus orderStatus){
        checkArgument(orderStatus != null ? true: false, "orderStatus must be provided");
    }



}
