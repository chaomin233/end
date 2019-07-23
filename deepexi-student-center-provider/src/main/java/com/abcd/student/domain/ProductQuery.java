package com.abcd.student.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

/**
 * 产品领域Query组件
 * @author yangxi
 *
 */
public class ProductQuery {

	/**
	 * 页码
	 */
	@Min(value = -1, message = "页码必须不能少于-1")
	private Integer page = 1;


	/**
	 * 每页显示数量
	 */
	@Min(value = 1, message = "每页显示数量必须大于0")
	private Integer size = 10;
	
	/**
	 * 产品价格
	 */
	@Min(value = 0, message = "价格必须大于等于0")
	private BigDecimal price;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}