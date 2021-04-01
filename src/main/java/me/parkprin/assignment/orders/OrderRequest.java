package me.parkprin.assignment.orders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Null;

public class OrderRequest {

    @Null
    private String message;

    protected OrderRequest(){}

    public OrderRequest(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("message", message)
                .toString();
    }


}
