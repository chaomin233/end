package com.abcd.pay.domain;

import java.math.BigDecimal;

import com.deepexi.util.pojo.AbstractObject;

/**
 * 产品领域DTO组件
 * @author yangxi
 *
 */
public class ProductDTO extends AbstractObject {

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