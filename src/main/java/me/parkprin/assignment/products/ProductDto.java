package me.parkprin.assignment.products;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;
import static java.util.Optional.ofNullable;
public class ProductDto {

    private Long seq;

    private String name;

    private String details;

    private int reviewCount;

    private LocalDateTime createAt;

    public ProductDto(ProductEntity source) {
        copyProperties(source, this);

        this.details = ofNullable(source.getDetails()).orElse(null);
        this.setSeq(source.getProductSeq());
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("name", name)
                .append("details", details)
                .append("reviewCount", reviewCount)
                .append("createAt", createAt)
                .toString();
    }

}
