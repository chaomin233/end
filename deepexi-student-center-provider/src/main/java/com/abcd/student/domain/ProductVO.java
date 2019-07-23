package com.abcd.student.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.deepexi.util.pojo.AbstractObject;

/**
 * 产品领域VO组件
 * @author yangxi
 *
 */
public class ProductVO extends AbstractObject {

	/**
	 * 产品名称
	 */
	@NotEmpty(message = "产品名称不能为空")
	@Size(min = 2, max = 30, message = "产品名称长度必须在2到30之间")
    private String name;
    
	@NotNull(message = "产品价格不能为空")
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