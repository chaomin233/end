package com.abcd.pay.domain;

import java.math.BigDecimal;

/**
 * 产品领域DO组件
 * @author yangxi
 *
 */
public class ProductDO extends SuperEntity {

    private String name;

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}